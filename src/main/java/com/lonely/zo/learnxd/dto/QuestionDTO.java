package com.lonely.zo.learnxd.dto;

import com.lonely.zo.learnxd.model.User;
import lombok.Data;

/**
 * @Author: ZO
 * @Description:
 * @Date: Create in 15:49 2019/8/10
 * @Modified By:
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private long gmtCreate;
    private long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private User user;
}
