package com.snow.fvmusic.aspect;

import com.snow.fvmusic.bo.CommonResult;
import com.snow.fvmusic.bo.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-07-19
 * @Description：
 * @Modified By：
 * @Version:
 */
@Aspect
@Order(2)
@Component
@Slf4j
public class BingResultAspect {

    @Around("execution(public * com.snow.fvmusic.controller.*.*.*(..)) && args(..,bindingResult)")
    public Object bingingResult(ProceedingJoinPoint proceedingJoinPoint, BindingResult bindingResult) throws Throwable{
        if (bindingResult.hasErrors()){
            log.info("参数校验失败。。。。。。。。。。");
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            HashMap<String, Object> bingingResultMap = new HashMap<>();
            fieldErrors.forEach(fieldError -> {
                String field = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                bingingResultMap.put("error field : ", field);
                bingingResultMap.put("error message : ", message);
            });
            return CommonResult.failed(ResultCode.FAILED,"参数校验失败", bingingResultMap);
        }
        return proceedingJoinPoint.proceed();
    }
}
