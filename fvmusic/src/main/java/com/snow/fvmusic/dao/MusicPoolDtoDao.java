package com.snow.fvmusic.dao;

import cn.hutool.db.Page;
import com.snow.fvmusic.dto.MusicPoolDto;
import com.snow.fvmusic.generator.entities.MusicPool;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-19
 * @Description：
 * @Modified By：
 * @Version:
 */
@Mapper
public interface MusicPoolDtoDao {
    /**
     * @return 推荐的MusicPool, gust_count最大
     */
   List<MusicPoolDto> getRecommendPool();

    /**
     * 获得相应类型的musicPools
     * @param styleId
     * @return
     */
   List<MusicPoolDto> getMusicPoolsByStyle(Long styleId);


}
