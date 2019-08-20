package com.lonely.zo.learnxd.controller;

import com.lonely.zo.learnxd.dto.PaginationDTO;
import com.lonely.zo.learnxd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author ZB
 * @Date 2019-07-31
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        //页面传递过来page和size两个参数，page是当前页码，size是每页的记录数目
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

        //在逻辑层进行分页查询处理
        PaginationDTO pagination = questionService.list(page,size);
        if (pagination == null){
            return "index";
        }else {
            model.addAttribute("pagination", pagination);
            return "index";
        }
    }
}
