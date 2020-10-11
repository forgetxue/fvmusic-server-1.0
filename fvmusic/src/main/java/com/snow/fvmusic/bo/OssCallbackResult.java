package com.snow.fvmusic.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-29
 * @Description：
 * @Modified By：
 * @Version:
 */
@Data
public class OssCallbackResult {
    @ApiModelProperty("文件名称")
    private String filename;
    @ApiModelProperty("文件大小")
    private String size;
    @ApiModelProperty("文件的mimeType")
    private String mimeType;
    @ApiModelProperty("图片文件的宽")
    private String width;
    @ApiModelProperty("图片文件的高")
    private String height;
}
