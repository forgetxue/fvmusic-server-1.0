package com.snow.fvmusic.bo;

import lombok.Getter;
import lombok.ToString;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-19
 * @Description：
 * @Modified By：
 * @Version:
 */
@Getter
@ToString
public enum ResultCode {
    SUCCESS(200),
    FAILED(500),
    UN_AUTHORIZED(502),
    NOT_FOUND(405),
    SERVER_ERROR(505),
    ILLEGAL(504);
    private final Integer code;
    private ResultCode(Integer code){
        this.code = code;
    }

}
