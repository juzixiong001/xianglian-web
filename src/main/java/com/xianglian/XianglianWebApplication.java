package com.xianglian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xianglian.mapper")
public class XianglianWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(XianglianWebApplication.class, args);
    }

}