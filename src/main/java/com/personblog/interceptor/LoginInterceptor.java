package com.personblog.interceptor;

import io.swagger.annotations.ApiModel;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApiModel(value = "登录过滤拦截器")
public class LoginInterceptor implements HandlerInterceptor {

    //在业务方法之前执行
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //从session中获取user
        if(request.getSession().getAttribute("user") == null){
                //如果别人知道你的后台的访问地址，不设置拦截器就可以直接访问了而无需登录
                //所以user为空，重定向到登录页面，给拦截掉（通过配置类指定拦截页面）
                response.sendRedirect("/admin");
                return false;
        }
        //已登录
        return true;
    }
}
