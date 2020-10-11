package com.snow.fvmusic.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.snow.fvmusic.dto.MusicPoolDto;
import com.snow.fvmusic.dto.PoolDto;
import com.snow.fvmusic.generator.entities.MusicPool;
import com.snow.fvmusic.vo.PoolDetailsVO;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-19
 * @Description：
 * @Modified By：
 * @Version:
 */
public interface MusicPoolDtoService {
    /**
     * 获取推荐的musicPools
     * @param currentPage
     * @param size
     * @return
     */
    PageInfo<MusicPoolDto> getRecommendMusicPool(Integer currentPage, Integer size);

    /**
     * 获取指定类型的pool
     * @param style
     * @param currentPage
     * @param size
     * @return
     */
    PageInfo<MusicPoolDto> getMusicPoolsByStyle(Long style, Integer currentPage, Integer size);

    /**
     * 添加pool
     * @param poolDto
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    int add(PoolDto poolDto);

    /**
     * 判断pool是否存在
     * @param styleId
     * @return
     */
    boolean existStyle(long styleId);

    /**
     * 删除pool
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    int delete(long id);

    /**
     * 根据id获取pool
     * @param poolId
     * @return
     */
    PoolDetailsVO getPoolDetailsVOById(Long poolId);

    /**
     * 使用名字搜索pool
     * @param name
     * @return
     */
    PoolDetailsVO getPoolDetailsVOByName(String name);
}
