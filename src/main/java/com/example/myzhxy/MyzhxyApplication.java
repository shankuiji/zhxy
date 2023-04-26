package com.example.myzhxy;

import org.mapstruct.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hongxiaobin
 */
@SpringBootApplication
@MapperScan("com.example.myzhxy.mapper")
public class MyzhxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyzhxyApplication.class, args);
    }

}
