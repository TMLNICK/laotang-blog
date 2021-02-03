package com.personblog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description : 处理异常页面并输出到日志中，带有状态码的不拦截，其他统一跳转到 error页面
 *                 前端报错的话就是跳转到 500 页面（或者其他的一些服务器异常）
 */
@ControllerAdvice   //@ControllerAdvice:拦截带有Controller注解的控制器
public class ControllerExceptionHandler {

    //获取日志对象，将异常记录到日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //@ExceptionHandler:表示这个方法是做异常处理的(是Exception或其子类的都可以做处理)
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandle(HttpServletRequest request, Exception e) throws Exception {


        //记录异常信息：出现异常的请求的路径
        /*
        控制台打印异常信息：Request URL: http://localhost:8080/, Exception : / by zero
         */
        logger.error("Request URL: {}, Exception : {}",request.getRequestURL(),e);

        //当标识了状态码的时候就不拦截
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        //将记录的异常信息返回到error页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
