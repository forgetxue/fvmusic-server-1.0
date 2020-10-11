package com.snow.fvmusic.service;

import com.snow.fvmusic.bo.OssCallbackResult;
import com.snow.fvmusic.bo.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-29
 * @Description：
 * @Modified By：
 * @Version:
 */
public interface OssService {
    /*
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
