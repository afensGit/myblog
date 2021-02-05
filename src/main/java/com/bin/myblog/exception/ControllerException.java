package com.bin.myblog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//拦截有@Controller注解的控制器的异常
@ControllerAdvice
public class ControllerException {

    //获取日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //标识该方法可以处理异常
    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(HttpServletRequest request, Exception e) throws Exception {
        //记录异常信息
        logger.error("Request URL : {} , Exception : {}" ,request.getRequestURL() ,e);
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        //将异常信息返回前端页面
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("URL",request.getRequestURL());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/error");
        System.out.println("我可以铺货任何异常");
        return modelAndView;
    }
}
