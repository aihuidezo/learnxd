package com.lonely.zo.learnxd.service;

import com.lonely.zo.learnxd.dto.PaginationDTO;
import com.lonely.zo.learnxd.dto.QuestionDTO;
import com.lonely.zo.learnxd.mapper.QuestionMapper;
import com.lonely.zo.learnxd.mapper.UserMapper;
import com.lonely.zo.learnxd.model.Question;
import com.lonely.zo.learnxd.model.User;
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

    public PaginationDTO list(Integer page, Integer size) {
        //分页所需参数，抽象为Pagination对象
        PaginationDTO paginationDTO= new PaginationDTO();

        Integer totalPage;

        Integer totalCount = questionMapper.count();
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
        if (questionMapper.count()==0){
            return null;
        }
        //分页查询
        List<Question> questions = questionMapper.list(offset,size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //初始化pagination对象的QuestionList
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
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

        Integer totalCount = questionMapper.countByuserid(userId);
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
        List<Question> questions = questionMapper.listByuserId(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //初始化pagination对象的QuestionList
        for (Question question : questions) {
            User u = userMapper.findById(question.getCreator());
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
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO=new QuestionDTO();
        //给questionDTO对象赋值user，user通过question的creator查找
        questionDTO.setUser(userMapper.findById(question.getCreator()));
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }
}
