package com.snow.fvmusic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.snow.fvmusic.generator.mapper", "com.snow.fvmusic.dao"})
public class FvmusicApplication {

    public static void main(String[] args) {

        SpringApplication.run(FvmusicApplication.class, args);

    }

}
