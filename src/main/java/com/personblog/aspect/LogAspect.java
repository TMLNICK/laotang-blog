package com.personblog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/*
在controller包下的所有类的所有方法执行之前（或者其他时间点自己也可以定义）打印日志信息
 */
@Aspect
@Component  //开启组件扫描，通过注解找到要扫描的对象（这里是扫描controller包下的类）
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // controller包下的所有类的所有方法
    @Pointcut("execution(* com.personblog.controller.*.*(..))")
    public void log(){}

    //前置通知：在目标方法之前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        //通过request拿到url和ip
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();//调用toString方法把StringBuffer类型转为toString
        String ip = request.getRemoteAddr();
        //获取到类名和方法名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);

        logger.info("Request : {}", requestLog);
    }


    //后置通知：在目标方法之后执行
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterRuturn(Object result) {
        //获取到目标方法的返回值
        logger.info("Result : {}", result);
    }

    private static class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
