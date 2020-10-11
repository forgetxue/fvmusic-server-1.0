package com.snow.fvmusic.bo;

import lombok.Data;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-19
 * @Description：
 * @Modified By：
 * @Version:
 */
@Data
public class CommonResult {
    private int code;
    private String message;
    private Object data;

    private CommonResult(int code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    private CommonResult(){

    }
    private  CommonResult(Object data){
        this.code = ResultCode.SUCCESS.getCode();
        this.message = "操作成功";
        this.data = data;
    }
    private  CommonResult(int code, String message){
        this.code = code;
        this.message = message;
    }
    private CommonResult(String message, Object data){
        this.message = message;
        this.data = data;
        this.code = 200;
    }
    public static CommonResult success(String message, Object data){
       return new CommonResult(message, data);
    }
    public static CommonResult success(Object data){
        return new CommonResult(data);
    }
    public static CommonResult failed(ResultCode code, String message){
        return new CommonResult(code.getCode(), message);
    }
    public static CommonResult failed(){
        return new CommonResult(ResultCode.FAILED.getCode(), "操作失败");
    }public static CommonResult failed(ResultCode code, String message, Object data){
        return new CommonResult(code.getCode(), message, data);
    }
    public static CommonResult failed(String message){
        return new CommonResult(ResultCode.FAILED.getCode(), message,null);
    }
    public static CommonResult unAuthorized(){
        return new CommonResult(ResultCode.UN_AUTHORIZED.getCode(), "您需要先登录才能完成相应操作");
    }
    public static CommonResult illegal(String message){
        return new CommonResult(ResultCode.ILLEGAL.getCode(), message, null);
    }

}
