package com.snow.fvmusic.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snow.fvmusic.bo.ApiException;
import com.snow.fvmusic.bo.ResultCode;
import com.snow.fvmusic.dao.MusicPoolDtoDao;
import com.snow.fvmusic.dto.MusicPoolDto;
import com.snow.fvmusic.dto.PoolDto;
import com.snow.fvmusic.generator.entities.MusicInfo;
import com.snow.fvmusic.generator.entities.MusicPool;
import com.snow.fvmusic.generator.entities.MusicStyle;
import com.snow.fvmusic.generator.entities.User;
import com.snow.fvmusic.generator.mapper.MusicInfoMapper;
import com.snow.fvmusic.generator.mapper.MusicPoolMapper;
import com.snow.fvmusic.generator.mapper.MusicStyleMapper;
import com.snow.fvmusic.generator.mapper.UserMapper;
import com.snow.fvmusic.service.MusicPoolDtoService;
import com.snow.fvmusic.vo.PoolDetailsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-19
 * @Description：
 * @Modified By：
 * @Version:
 */
@Service
@Slf4j
public class MusicPoolDtoServiceImpl implements MusicPoolDtoService {
    @Resource
    private MusicPoolDtoDao musicPoolDtoDao;
    @Resource
    private MusicPoolMapper musicPoolMapper;
    @Resource
    private MusicStyleMapper musicStyleMapper;
    @Resource
    private MusicInfoMapper musicInfoMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public PageInfo<MusicPoolDto> getRecommendMusicPool(Integer currentPage, Integer size) {
        log.info("Get Recommend Music Pool ,access DataBase");
        PageHelper.startPage(currentPage, size);
        List<MusicPoolDto> list = musicPoolDtoDao.getRecommendPool();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<MusicPoolDto> getMusicPoolsByStyle(Long style, Integer currentPage, Integer size) {
        // check the style is exists
       if(!existStyle(style)) throw new ApiException(ResultCode.NOT_FOUND, "不存在该音乐分类");

       List<MusicPoolDto> list = musicPoolDtoDao.getMusicPoolsByStyle(style);
       PageHelper.startPage(currentPage, size);
       return new PageInfo<>(list);
    }

    @Override
    public int add(PoolDto poolDto) {
        if (!existStyle(poolDto.getMusicTypeId())) throw new ApiException(ResultCode.NOT_FOUND, "不存在该音乐类型");
        MusicPool pool = new MusicPool();
        // 判断pool的name是否已经被占用
        if (isPoolNameExists(poolDto.getName())) throw new ApiException(ResultCode.ILLEGAL, "该名字已经被占用，请换个名字");
        // 判断mainMusic 是否已经存在
        String mainMusicUrl = poolDto.getMainMusicUrl();
        System.out.println(mainMusicUrl);
        Integer wangyiMusicId = Integer.valueOf(mainMusicUrl.substring(mainMusicUrl.indexOf('=') + 1));
        QueryWrapper<MusicInfo> musicInfoWrapper = new QueryWrapper<>();
        musicInfoWrapper.eq("wangyi_id", wangyiMusicId);
        MusicInfo exit = musicInfoMapper.selectOne(musicInfoWrapper);
        if(exit != null){
            pool.setMainMusicInfo(exit.getId());
        }else{
            MusicInfo musicInfo = new MusicInfo();
            musicInfo.setWangyiId(wangyiMusicId);
            musicInfo.setStyleId(poolDto.getMusicTypeId());
            musicInfoMapper.insert(musicInfo);
            // 查询出刚刚插入的id
            musicInfoWrapper.clear();
            musicInfoWrapper.inSql("id", "select max(id) from music_info");
            MusicInfo justInserted = musicInfoMapper.selectOne(musicInfoWrapper);
            if (justInserted == null) throw new ApiException(ResultCode.SERVER_ERROR, "music info id 获取异常");
            pool.setMainMusicInfo(justInserted.getId());
        }
        pool.setName(poolDto.getName());
        pool.setIntroduce(poolDto.getIntroduce());
        pool.setCoverUrl(poolDto.getCoverUrl());
        pool.setCreateUserId(poolDto.getCreateUserId());
        int insert = musicPoolMapper.insert(pool);
        if (insert > 0){
            User user = userMapper.selectById(poolDto.getCreateUserId());
            user.setCreatePools(user.getCreatePools() + 1);
            userMapper.updateById(user);
        }
        return insert;
    }
    @Override
     public  boolean existStyle(long styleId){
        MusicStyle one = musicStyleMapper.selectById(styleId);
        return one != null;
    }

    @Override
    public int delete(long id) {
        QueryWrapper<MusicPool> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.select("main_music_info");
        List<Object> list = musicPoolMapper.selectObjs(wrapper);
        int del = 0;
        if (!CollUtil.isEmpty(list)){
            musicInfoMapper.deleteById((Long)list.get(0));
             del = musicPoolMapper.deleteById(id);
        }
        return del;
    }

    @Override
    public PoolDetailsVO getPoolDetailsVOById(Long poolId) {
        PoolDetailsVO poolDetails = new PoolDetailsVO();
        // 查询出poolInfo
        MusicPool musicPool = musicPoolMapper.selectById(poolId);
        if (musicPool == null) throw new ApiException(ResultCode.NOT_FOUND, "不存在该id的pool");
        poolDetails.setMusicPool(musicPool);
        Long createUserId = musicPool.getCreateUserId();
        User user = userMapper.selectById(createUserId);
        Long mainMusicId = musicPool.getMainMusicInfo();
        MusicInfo musicInfo = musicInfoMapper.selectById(mainMusicId);
        if (user == null) throw new ApiException(ResultCode.ILLEGAL, "不合法的pool，create user 为空");
        if (musicInfo != null){
            poolDetails.setMainMusicWangyiId(musicInfo.getWangyiId());
        }
        poolDetails.setCreateUser(user);
        return poolDetails;
    }

    @Override
    public PoolDetailsVO getPoolDetailsVOByName(String name) {
        QueryWrapper<MusicPool> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        List<Object> list = musicPoolMapper.selectObjs(wrapper);
        if (!CollUtil.isEmpty(list)){
            Long poolId = (Long) list.get(0);
            return getPoolDetailsVOById(poolId);
        }
        throw new ApiException(ResultCode.NOT_FOUND, "不能存在该pool");
    }

    /**
     * 判断音乐池的名字是否已经存在
     * @param name
     * @return
     */
    private boolean isPoolNameExists(String name){
        name = name.replace(" ", "");
        QueryWrapper<MusicPool> poolWrapper = new QueryWrapper<>();
        poolWrapper.eq("name", name);
        return musicPoolMapper.selectOne(poolWrapper) != null;
    }

}
