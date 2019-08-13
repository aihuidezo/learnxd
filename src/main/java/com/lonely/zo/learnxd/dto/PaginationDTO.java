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
    private List<QuestionDTO> questions;
    private boolean showPrevious;//向前按钮
    private boolean showFirstPage;//首页
    private boolean showNext;//向后按钮
    private boolean showEndPage;//尾页

    private Integer page;//当前页
    private List<Integer> pages = new ArrayList<>();


    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
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
        if (pages.contains(totalPage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }
    }
}
