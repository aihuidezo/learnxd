package com.lonely.zo.learnxd.service;

import com.lonely.zo.learnxd.enums.CommentTypeEnum;
import com.lonely.zo.learnxd.exception.CustomizeErrorCode;
import com.lonely.zo.learnxd.exception.CustomizeException;
import com.lonely.zo.learnxd.mapper.CommentMapper;
import com.lonely.zo.learnxd.mapper.QuestionMapper;
import com.lonely.zo.learnxd.model.Comment;
import com.lonely.zo.learnxd.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ZhuBo
 * @Description:
 * @Date: Create in 10:57 2019/8/26
 * @Modified By:
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public void insert(Comment comment){
        if (comment.getParentId()==null||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType()==null|| !CommentTypeEnum.isExit(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            comment.setGmtCreate(System.currentTimeMillis());
            comment.setGmtModified(System.currentTimeMillis());
            commentMapper.insert(comment);

        }else{

            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
        }

        commentMapper.insert(comment);
    }
}
