package com.snow.fvmusic.vo;

import com.snow.fvmusic.generator.entities.MusicPool;
import com.snow.fvmusic.generator.entities.User;
import lombok.Data;

import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-10-05
 * @Description：
 * @Modified By：
 * @Version:
 */
@Data
public class PoolDetailsVO {
    private User createUser;
    private MusicPool musicPool;
    private Integer mainMusicWangyiId;
}
