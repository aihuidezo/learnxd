package com.lonely.zo.learnxd.service;

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
    public List<QuestionDTO> list() {
        List<Question> questions=questionMapper.list();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : questions) {
            User u=userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(u);
            questionDTOList.add(questionDTO);
        }
        return  questionDTOList;
    }
}
