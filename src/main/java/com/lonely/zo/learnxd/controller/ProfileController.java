package com.lonely.zo.learnxd.controller;

import com.lonely.zo.learnxd.dto.PaginationDTO;
import com.lonely.zo.learnxd.model.User;
import com.lonely.zo.learnxd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZO
 * @Description:
 * @Date: Create in 14:23 2019/8/15
 * @Modified By:
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          HttpServletRequest request,
                          Model model){
        User user=(User)request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if ("repies".equals(action)){
            model.addAttribute("section","repies");
            model.addAttribute("sectionName","最新回复");
        }
        PaginationDTO paginationDTO=questionService.listByuserid(user.getId(),page,size);
        if (paginationDTO==null){
            return "profile";
        }else {
            model.addAttribute("pagination", paginationDTO);
            return "profile";
        }
    }
}
