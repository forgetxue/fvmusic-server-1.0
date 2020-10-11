package com.snow.fvmusic.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.sql.visitor.functions.Bin;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.snow.fvmusic.aspect.BingResultAspect;
import com.snow.fvmusic.bo.ApiException;
import com.snow.fvmusic.bo.CommonResult;
import com.snow.fvmusic.bo.ResultCode;
import com.snow.fvmusic.dto.MusicPoolDto;
import com.snow.fvmusic.dto.PoolDto;
import com.snow.fvmusic.generator.entities.MusicPool;
import com.snow.fvmusic.generator.entities.MusicStyle;
import com.snow.fvmusic.generator.service.MusicPoolService;
import com.snow.fvmusic.generator.service.MusicStyleService;
import com.snow.fvmusic.service.MusicPoolDtoService;
import com.snow.fvmusic.vo.PoolDetailsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.swing.text.Style;
import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-19
 * @Description：
 * @Modified By：
 * @Version:
 */
@Api("musicPool接口")
@RestController
@Slf4j
@RequestMapping("/pools")
public class MusicPoolController {
    @Resource
    private MusicPoolDtoService musicPoolDtoService;
    @Resource
    private MusicPoolService musicPoolService;
    @Resource
    private MusicStyleService musicStyleService;
    @PostMapping("/recommend")
    public CommonResult getRecommendMusicPools(
       @RequestParam(name="page", required = false, defaultValue = "1") Integer page,
       @RequestParam(name="size", required = false, defaultValue = "4") Integer size
    ){
        PageInfo<MusicPoolDto> res = musicPoolDtoService.getRecommendMusicPool(page, size);
        return res == null ? CommonResult.failed("Music Pool 获取失败, Server Error") : CommonResult.success(res);
    }

    @PostMapping("/style")
    @ApiOperation("获取指定类型的musicPool分页")
    public CommonResult getMusicPoolByStyle(
            @RequestParam(name="style", required = true) Long style,
            @RequestParam(name="current", required = false,defaultValue = "1") Integer currentPage,
            @RequestParam(name="size", required = false, defaultValue = "5") Integer size
    ){
        PageInfo<MusicPoolDto> page = musicPoolDtoService.getMusicPoolsByStyle(style, currentPage, size);
        return page == null ? CommonResult.failed(ResultCode.NOT_FOUND,"获取失败"):CommonResult.success(page);
    }
    @PostMapping("/create")
    @ApiOperation("添加musicPool-json")
    public CommonResult addPool(@RequestBody PoolDto poolDto, BindingResult bindingResult){
        int add = musicPoolDtoService.add(poolDto);
        return add > 0 ? CommonResult.success("创建成功") : CommonResult.failed("添加失败,可能是服务器内部错诶");

    }
    @PostMapping("/update")
    @ApiOperation("更新pool-json")
    public CommonResult updatePool(@RequestBody MusicPool musicPool, BingResultAspect bingResultAspect){
        if (!musicPoolDtoService.existStyle(musicPool.getMusicTypeId())) return CommonResult.failed(ResultCode.NOT_FOUND, "不存在该类型的音乐");
        return musicPoolService.updateById(musicPool) ? CommonResult.success("更新成功") : CommonResult.failed("更新失败");
    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除pool,并删除对应的main_music_info")
    public CommonResult delete(@PathVariable("id") long id){
        int delete = musicPoolDtoService.delete(id);
        return delete > 0 ? CommonResult.success("删除成功") : CommonResult.failed("删除失败，请重试");
    }

    @GetMapping("/styles")
    @ApiOperation("获取所有的音乐分类")
    public CommonResult getMusicStyles(){
        List<MusicStyle> list = musicStyleService.list();
        return CommonResult.success(list);
    }
    @GetMapping("/poolDetails/{poolId}")
    @ApiOperation("poolDetails页面pool的详细信息获取")
    public CommonResult getPoolDetailsInfo(@PathVariable("poolId") Long poolId){
        PoolDetailsVO result = musicPoolDtoService.getPoolDetailsVOById(poolId);
        return CommonResult.success(result);

    }
    @GetMapping("/poolDetails/byName/{name}")
    @ApiOperation("根据名字搜索pool")
    public CommonResult getPoolDetailsByName(@PathVariable("name") String name){
        if (StrUtil.isEmpty(name)) throw new ApiException(ResultCode.ILLEGAL, "参数name不能为空");
        PoolDetailsVO result = musicPoolDtoService.getPoolDetailsVOByName(name);
        return CommonResult.success(result);
    }
}
