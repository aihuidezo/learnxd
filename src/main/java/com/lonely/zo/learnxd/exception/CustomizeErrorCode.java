package com.lonely.zo.learnxd.exception;

/**
 * @Author: ZhuBo
 * @Description:
 * @Date: Create in 11:00 2019/8/22
 * @Modified By:
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("你找的问题不存在，请换个问题试试！-_-");

    @Override
    public String getMessage() {
        return message;
    }

    private  String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
