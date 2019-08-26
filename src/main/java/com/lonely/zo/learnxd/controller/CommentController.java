package com.lonely.zo.learnxd.controller;

import com.lonely.zo.learnxd.dto.CommentDTO;
import com.lonely.zo.learnxd.dto.ResultDTO;
import com.lonely.zo.learnxd.exception.CustomizeErrorCode;
import com.lonely.zo.learnxd.model.Comment;
import com.lonely.zo.learnxd.model.User;
import com.lonely.zo.learnxd.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZhuBo
 * @Description:
 * @Date: Create in 15:02 2019/8/23
 * @Modified By:
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService  commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(HttpServletRequest request,
                       @RequestBody CommentDTO commentDTO){

        User user=(User)request.getSession().getAttribute("user");
        System.out.println(user);
        if (user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setCommentator(1L);
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf() ;
    }
}
