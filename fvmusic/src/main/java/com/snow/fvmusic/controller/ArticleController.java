package com.snow.fvmusic.controller;

import com.alibaba.druid.sql.visitor.functions.Bin;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.snow.fvmusic.bo.CommonResult;
import com.snow.fvmusic.bo.ResultCode;
import com.snow.fvmusic.dto.ArticleDto;
import com.snow.fvmusic.generator.entities.Article;
import com.snow.fvmusic.generator.service.ArticleService;
import com.snow.fvmusic.service.ArticleVOService;
import com.snow.fvmusic.vo.ArticleVO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-28
 * @Description：
 * @Modified By：
 * @Version:
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleVOService articleVOService;
    @Resource
    private ArticleService articleService;

    @ApiOperation("获取指定音乐池的文章——url-encoding,默认按发布日期排序")
    @PostMapping("/poolArticles")
    public CommonResult getArticlesOfPool(
            @RequestParam(name="current", defaultValue = "1", required = false) Integer current,
            @RequestParam(name="size", defaultValue = "11", required = false) Integer size,
            @RequestParam(name="poolId", required = false) Long poolId

    ){
        PageInfo<ArticleVO> page = articleVOService.getArticlesOfMusicPool(poolId, current, size);
        return CommonResult.success(page);
    }

    @ApiOperation("获取音乐池中文章指定排序")
    @GetMapping("/poolArticlesByOrder")
    public CommonResult getArticlesOfPoolByOrder(
            @RequestParam(name="poolId", required = true) Long poolId,
            @RequestParam(name="current", defaultValue = "1", required = false) Integer current,
            @RequestParam(name="size", defaultValue = "11", required = false) Integer size,
            @RequestParam(name="orderField", required = true) String orderField
    ){
        PageInfo<ArticleVO> article = articleVOService.getArticleOfMusicPoolByOrder(poolId, orderField, "desc", current, size);
        return CommonResult.success(article);
    }
    @PostMapping("/create")
    @ApiOperation("发布分享")
    public CommonResult create(@RequestBody ArticleDto articleDto, BindingResult bindingResult){
        int insertCount = articleVOService.create(articleDto);
        return insertCount > 0 ? CommonResult.success("发布成功") : CommonResult.failed("上传失败，服务器错诶");
    }
    @GetMapping("/{id}")
    @ApiOperation("根据id获取文章")
    public CommonResult getArticleById(@PathVariable("id") Long id){
        ArticleVO article = articleVOService.getArticleVOById(id);
        return article != null ? CommonResult.success(article) :CommonResult.failed(ResultCode.NOT_FOUND, "不存在该文章");
    }
}
