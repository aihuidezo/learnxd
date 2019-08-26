package com.lonely.zo.learnxd.controller;

import com.lonely.zo.learnxd.dto.QuestionDTO;
import com.lonely.zo.learnxd.mapper.QuestionMapper;
import com.lonely.zo.learnxd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: ZhuBo
 * @Description:
 * @Date: Create in 11:12 2019/8/20
 * @Modified By:
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        questionService.incView(id);
        QuestionDTO questionDTO= questionService.getById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
