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
        Integer totalCount = questionMapper.count();
        //初始化pagination对象
        paginationDTO.setPagination(totalCount,page,size);


        if (page<1){
            page=1;
        }
        if (page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
        //offset查询偏移量，size查询条数
        Integer offset=size*(page-1);
        //分页查询
        List<Question> questions = questionMapper.list(offset,size);
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
}
