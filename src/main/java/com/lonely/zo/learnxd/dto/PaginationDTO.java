package com.lonely.zo.learnxd.dto;

import com.lonely.zo.learnxd.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZO
 * @Description:
 * @Date: Create in 17:10 2019/8/13
 * @Modified By:
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;//每页展示的问题列表
    private boolean showPrevious;//向前按钮
    private boolean showFirstPage;//首页
    private boolean showNext;//向后按钮
    private boolean showEndPage;//尾页

    private Integer page;//当前页
    private List<Integer> pages = new ArrayList<>();//展示页面列
    private Integer totalPage;//最大分页数

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage=totalPage;
        this.page=page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示首页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示尾页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
