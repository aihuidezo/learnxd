package com.lonely.zo.learnxd.enums;

import com.lonely.zo.learnxd.model.Comment;

/**
 * @Author: ZhuBo
 * @Description:
 * @Date: Create in 10:52 2019/8/26
 * @Modified By:
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public static boolean isExit(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType() == type){
                return true;
            }
        }
        return false;
    }

    public Integer getType( ) {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
