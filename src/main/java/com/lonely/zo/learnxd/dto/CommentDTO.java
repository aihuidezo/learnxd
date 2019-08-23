package com.lonely.zo.learnxd.dto;

import lombok.Data;

/**
 * @Author: ZhuBo
 * @Description:
 * @Date: Create in 15:15 2019/8/23
 * @Modified By:
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
