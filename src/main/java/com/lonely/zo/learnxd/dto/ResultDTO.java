package com.lonely.zo.learnxd.dto;

import com.lonely.zo.learnxd.exception.CustomizeErrorCode;
import com.lonely.zo.learnxd.exception.CustomizeException;
import lombok.Data;

/**
 * @Author: ZhuBo
 * @Description:
 * @Date: Create in 10:33 2019/8/26
 * @Modified By:
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }

    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }


}
