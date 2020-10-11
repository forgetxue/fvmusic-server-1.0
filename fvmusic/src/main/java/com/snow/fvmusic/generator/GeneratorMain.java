package com.snow.fvmusic.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

/**
 * @Author ：snow
 * @Date ：Created in 2020-07-11
 * @Description：
 * @Modified By：
 * @Version:
 */
public class GeneratorMain {
    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String basePath = System.getProperty("user.dir");
//        basePath += "/generator";
        gc.setOutputDir(basePath + "/src/main/java");
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setAuthor("snow");
        gc.setSwagger2(true);
        gc.setEntityName("%s");
        gc.setMapperName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setIdType(IdType.AUTO);

        generator.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig ds = new DataSourceConfig();
        ds.setDbType(DbType.MYSQL);
        ds.setDriverName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("196");
        ds.setUrl("jdbc:mysql://localhost:3306/fv_music?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        generator.setDataSource(ds);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.snow.generator");
        pc.setEntity("entities");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        generator.setPackageInfo(pc);

        // 配置模板
        TemplateConfig tc = new TemplateConfig();
        tc.setEntity("templates/entity.java");
        tc.setXml("");
        generator.setTemplate(tc);

        // 数据库表配置
        StrategyConfig sc = new StrategyConfig();
        sc.setSkipView(true);
        sc.setNaming(NamingStrategy.underline_to_camel); // 表名映射
        sc.setColumnNaming(NamingStrategy.underline_to_camel); // 字段映射
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);
        sc.setEntitySerialVersionUID(true);
        sc.setEntityColumnConstant(true);// 将Mysql字段名生成静态常量
        sc.setEntityTableFieldAnnotationEnable(true); // 注释
//        sc.setInclude(("article,comment,great_comments,music_info,music_pool,music_pool_article_relation,music_style,recommend_music_style_relation," +
//                "user,user_concern_relation,user_fans_relation").split(","));
        sc.setInclude("article");
        generator.setStrategy(sc);
        generator.setTemplateEngine(new VelocityTemplateEngine());
        generator.execute();
    }
}
