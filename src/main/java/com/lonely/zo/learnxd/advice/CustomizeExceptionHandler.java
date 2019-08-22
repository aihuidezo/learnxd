package com.lonely.zo.learnxd.advice;

import com.lonely.zo.learnxd.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: ZhuBo
 * @Description:
 * @Date: Create in 9:58 2019/8/22
 * @Modified By:
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle( Throwable e, Model model) {
        if (e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage());
        }else{
            model.addAttribute("message","服务冒烟了,要不然你稍后试试！-_-");
        }
        return new ModelAndView("error");
    }
}
