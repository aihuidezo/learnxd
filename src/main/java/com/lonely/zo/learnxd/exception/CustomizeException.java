package com.lonely.zo.learnxd.exception;

/**
 * @Author: ZhuBo
 * @Description:
 * @Date: Create in 10:38 2019/8/22
 * @Modified By:
 */
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
