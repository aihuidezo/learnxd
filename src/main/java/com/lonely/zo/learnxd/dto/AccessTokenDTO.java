package com.lonely.zo.learnxd.dto;

import lombok.Data;

/**
 * @Description
 * @Author ZB
 * @Date 2019-08-01
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
