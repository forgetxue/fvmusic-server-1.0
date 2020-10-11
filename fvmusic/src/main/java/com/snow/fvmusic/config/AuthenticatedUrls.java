package com.snow.fvmusic.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ：snow
 * @Date ：Created in 2020-09-20
 * @Description：
 * @Modified By：
 * @Version:
 */
@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "secure.authenticated")
public class AuthenticatedUrls {
    private List<String> urls = new ArrayList<>();
}
