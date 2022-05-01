package com.github.qilihui.oj.judge.server.handler;

import cn.hutool.json.JSONUtil;
import com.github.qilihui.oj.judge.core.model.JudgerResult;
import com.github.qilihui.oj.judge.server.config.LangConfig;
import com.github.qilihui.oj.judge.server.service.Executor;
import com.github.qilihui.oj.judge.server.service.JudgeCompiler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 消息处理器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-01-07 14:58
 */
@Component
@Slf4j
public class MessageHandler {
    @Resource
    private JudgeCompiler compiler;
    @Resource
    private Executor executor;
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "test", containerFactory = "ackContainerFactory")
    public void handleMessage(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            log.info("收到消息: {}", message);
            LangConfig langConfig = JSONUtil.toBean(message, LangConfig.class);
            this.process(langConfig);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

    public void process(LangConfig config) {
        Response response = new Response();
        JudgerResult compile = compiler.compile(config);
        response.setCompilerResult(compile);
        if (compile.getResult() != 0) {
            kafkaTemplate.send("judge.result", JSONUtil.toJsonStr(response));
            return;
        }
        List<JudgerResult> execute = executor.execute(config);
        response.setRunResult(execute);
        kafkaTemplate.send("judge.result", JSONUtil.toJsonStr(response));
    }

    @Data
    private static class Response {
        private JudgerResult compilerResult;
        private List<JudgerResult> runResult;
    }
}
