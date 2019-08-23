package com.lonely.zo.learnxd.service;

import com.lonely.zo.learnxd.dto.PaginationDTO;
import com.lonely.zo.learnxd.dto.QuestionDTO;
import com.lonely.zo.learnxd.exception.CustomizeErrorCode;
import com.lonely.zo.learnxd.exception.CustomizeException;
import com.lonely.zo.learnxd.mapper.QuestionExtMapper;
import com.lonely.zo.learnxd.mapper.QuestionMapper;
import com.lonely.zo.learnxd.mapper.UserMapper;
import com.lonely.zo.learnxd.model.Question;
import com.lonely.zo.learnxd.model.QuestionExample;
import com.lonely.zo.learnxd.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZO
 * @Description:
 * @Date: Create in 15:50 2019/8/10
 * @Modified By:
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO list(Integer page, Integer size) {
        //分页所需参数，抽象为Pagination对象
        PaginationDTO paginationDTO= new PaginationDTO();

        Integer totalPage;

        Integer totalCount =(int) questionMapper.countByExample(new QuestionExample());
        //计算totalPage
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //规范页码s
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        //初始化pagination对象
        paginationDTO.setPagination(totalPage,page);

        //offset查询偏移量，size查询条数
        Integer offset=size*(page-1);
        //分页查询
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //初始化pagination对象的QuestionList
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }

    public PaginationDTO listByuserid(Integer userId, Integer page, Integer size) {
        //分页所需参数，抽象为Pagination对象
        PaginationDTO paginationDTO= new PaginationDTO();
        Integer totalPage;

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount =(int) questionMapper.countByExample(questionExample);

        if (totalCount==0){
            return null;
        }

        //计算totalPage
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //规范页码s
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        //初始化pagination对象
        paginationDTO.setPagination(totalPage,page);


        //offset查询偏移量，size查询条数
        Integer offset=size*(page-1);


        //分页查询
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //初始化pagination对象的QuestionList
        for (Question question : questions) {
            //通过问题的creator,即问题的创建者的id查找user
            User u = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(u);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }
    //通过id得到question，并但会question对象
    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        //数据库中不存在该id的问题，返回异常处理
        if (question==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO=new QuestionDTO();
        //给questionDTO对象赋值user，user通过question的creator查找user
        questionDTO.setUser(userMapper.selectByPrimaryKey(question.getCreator()));
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }
    public void createOrUpdate(Question question){
        if (question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            Question updatequestion = new Question();
            updatequestion.setGmtModified(System.currentTimeMillis());
            updatequestion.setTag(question.getTitle());
            updatequestion.setTag(question.getTag());
            updatequestion.setDescription(question.getDescription());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updatequestion, example);
            if (updated != 1){
                //问题不存在，返回QUESTION_NOT_FOUND异常
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
