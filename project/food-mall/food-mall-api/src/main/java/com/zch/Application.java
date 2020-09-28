package com.zch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zch
 * @date 2020/9/28 9:51
 */
@SpringBootApplication
//扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "com.zch.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
