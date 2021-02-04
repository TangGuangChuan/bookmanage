package com.keji.bookmanage.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @auther tangguangchuan
 * @date 2021/2/4 下午2:15
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
    @Pointcut("execution(* com.keji.bookmanage.controller..*.*(..))")
    public void webLog(){

    }
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        //接收到请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求内容
        log.info("URL"+request.getRequestURL().toString());
        log.info("HTTP_METHOD:"+request.getMethod());
        log.info("IP:"+request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        Map<String,Object> paraMap = new HashMap<>();
        while(enu.hasMoreElements()){
            String name = enu.nextElement();
            paraMap.put(name,request.getParameter(name));
        }
        log.info("Parameters:"+paraMap.toString());
    }

    @AfterReturning(value = "webLog()",returning = "returnOb")
    public void doAfterReturning(JoinPoint joinPoint,Object returnOb){
        log.info("return:"+returnOb);
    }

    @AfterThrowing(value = "webLog()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Exception e){
        log.info("方法"+joinPoint.getSignature().getName()+"抛异常"+e);
    }
}
