package com.github.qilihui.oj.judge.server.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.github.qilihui.oj.judge.server.config.JudgerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * @author qilihui
 * @date 2021/12/4 10:32 下午
 */
@RestController
public class HeartbeatController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${judger.version}")
    private String version;

    @RequestMapping("/ping")
    public String ping() {
        return version;
    }

    @PostMapping("/judge")
    public Map<String, Object> judge(@RequestBody JudgerRequest config) {
        config.setSubmissionId(UUID.randomUUID().toString());
        kafkaTemplate.send("test", JSONUtil.toJsonStr(config));
        Map<String, Object> map = MapUtil.newHashMap();
        map.put("submissionId", config.getSubmissionId());
        return map;
    }
}
