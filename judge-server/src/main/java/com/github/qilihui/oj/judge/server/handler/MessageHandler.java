package com.github.qilihui.oj.judge.server.handler;

import cn.hutool.json.JSONUtil;
import com.github.qilihui.oj.judge.server.config.JudgerRequest;
import com.github.qilihui.oj.judge.server.config.JudgerResponse;
import com.github.qilihui.oj.judge.server.exception.ResponseCode;
import com.github.qilihui.oj.judge.server.service.Executor;
import com.github.qilihui.oj.judge.server.service.JudgeCompiler;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qlh
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
            this.process(JSONUtil.toBean(message, JudgerRequest.class));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

    public void process(JudgerRequest config) {
        JudgerResponse response = new JudgerResponse();
        response.setSubmissionId(config.getSubmissionId());
        try {
            JudgeCompiler.CompilerDTO compile = compiler.compile(config);
            if (compile.getJudgerResult().getResult() == 0) {
                List<JudgerResponse.RunDTO> execute = executor.execute(config);
                response.setRun(execute);
            } else {
                response.result(ResponseCode.R_1);
                response.setMsg(compile.getInfo());
            }
        } catch (Exception e) {
            log.error("submissionId:{}", config.getSubmissionId(), e);
            response.result(ResponseCode.P_1000001);
        }
        log.info("{}", response);
        kafkaTemplate.send("judge.result", JSONUtil.toJsonStr(response));
    }

}
