package com.snow.fvmusic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-04-10
 **/
@Configuration
public class CorsConfig {
    @Bean(name="corsFilter")
    CorsFilter corsFilter(){
        return new CorsFilter(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest httpServletRequest) {
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowCredentials(true);
                cfg.addAllowedOrigin("http://localhost:8080");
                cfg.addAllowedMethod("*");
                cfg.addAllowedHeader("*");
                cfg.setMaxAge((long) 3600);
                return cfg;
            }
        });
    }
}
