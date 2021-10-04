package com.github.qilihui.oj.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qilihui
 * @date 2021/7/24 0:08
 */
@SpringBootApplication
@MapperScan({"com.github.qilihui.oj.dao.mapper"})
public class OnlineJudgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineJudgeApplication.class, args);
    }
}
