package com.snow.fvmusic.bo;

import org.aspectj.apache.bcel.classfile.Code;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-20
 * @Description：
 * @Modified By：
 * @Version:
 */
public class ApiException extends RuntimeException {
    private String message;
    private ResultCode code;
    public  ApiException(ResultCode resultcode, String message){
        super(message);
        this.message = message;
        this.code = resultcode;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
    public ResultCode resultCode(){
        return this.code;
    }
}
