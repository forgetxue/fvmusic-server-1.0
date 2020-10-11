package com.snow.fvmusic.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;


/**
 * @Author ：snow
 * @Date ：Created in 2020-07-09
 * @Description：
 * @Modified By：
 * @Version:
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.snow.fvmusic.controller.*.*(..))")
    public void webLog(){}


    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("REQUEST IP============>" + request.getRemoteUser());
        log.info("REQUEST URL============>" + request.getRequestURL().toString());
        log.info("REQUEST URI============>" + request.getRequestURI().toString());
        log.info("REQUEST IP============>" + request.getRemoteHost());
        log.info("REQUEST METHOD============>" + request.getMethod());
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("REQUEST PARAMS========> :");
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            log.info("{} ========> {}", entry.getKey(), entry.getValue());
        }
    }


    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret){
        log.info("RETURN " + ret);
        log.info("SPEND TIME " + (System.currentTimeMillis() - startTime.get()));
        startTime.remove();
    }

}
