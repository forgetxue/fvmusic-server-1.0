package com.snow.fvmusic.bo;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author ：snow
 * @Date ：Created in 2020-07-06
 * @Description：
 * @Modified By：
 * @Version:
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler( value = {ApiException.class} )
    public CommonResult handler(ApiException e){
        return CommonResult.failed(e.resultCode(), e.getMessage());
    }

}
