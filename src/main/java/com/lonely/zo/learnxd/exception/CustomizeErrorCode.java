package com.lonely.zo.learnxd.exception;

/**
 * @Author: ZhuBo
 * @Description:
 * @Date: Create in 11:00 2019/8/22
 * @Modified By:
 */
public  enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不存在，请换个问题试试！-_-"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复！-_-"),
    NO_LOGIN(2003,"未登录，不能进行评论，请先登录！-_-"),
    SYSTEM_ERROR(2004,"服务冒烟了,要不然你稍后试试！-_-"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在！-_-"),
    COMMENT_NOT_FOUND(2006,"你回复的评论已经不在了！-_-");

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private  String message;


    CustomizeErrorCode( Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
