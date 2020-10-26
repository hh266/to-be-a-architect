package com.zch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zch
 * @date 2020/9/28 9:51
 */
@SpringBootApplication
//扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "com.zch.mapper")
//扫描相关包以及其组件包
@ComponentScan(basePackages = {"com.zch","org.n3r.idworker"})
//开启定时任务
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
