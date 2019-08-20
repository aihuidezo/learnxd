package com.lonely.zo.learnxd.dto;

import lombok.Data;

/**
 * @Description
 * @Author ZB
 * @Date 2019-08-01
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
