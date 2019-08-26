package com.lonely.zo.learnxd.exception;

/**
 * @Author: ZhuBo
 * @Description:
 * @Date: Create in 10:38 2019/8/22
 * @Modified By:
 */
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code=errorCode.getCode();
        this.message = errorCode.getMessage();
    }
    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
