package com.personblog.interceptor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ApiModel(value = "添加配置类注解，继承WebMvcConfigurer，指定拦截内容的配置类")
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器
     * @param registry
     */

    @ApiModelProperty(value = "重写方法，指定要拦截的路径")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //把登录拦截的对象传过来
        registry.addInterceptor(new LoginInterceptor())
                //admin下的所有路径全部拦截，但不包括admin 和 login 页面
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
