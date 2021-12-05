package com.github.qilihui.oj.judge.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qilihui
 * @date 2021/12/4 10:32 下午
 */
@RestController
public class HeartbeatController {
    @Value("${judger.version}")
    private String version;

    @RequestMapping("/ping")
    public String ping() {
        return version;
    }
}
