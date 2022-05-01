package com.github.qilihui.oj.judge.server.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.crypto.SecureUtil;
import com.github.qilihui.oj.judge.core.JudgerCore;
import com.github.qilihui.oj.judge.core.model.JudgerConfig;
import com.github.qilihui.oj.judge.core.model.JudgerResult;
import com.github.qilihui.oj.judge.server.config.LangConfig;
import com.github.qilihui.oj.judge.server.handler.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qilihui
 * @date 2022/1/9 12:15 上午
 */
@Slf4j
@Service
public class Executor {
    @Value("${judger.baseDir}")
    private String baseDir;

    @Value("${judger.judgerRunLogPath}")
    private String judgerRunLogPath;

    public List<MessageHandler.JudgerResultResponse> execute(LangConfig config) {
        String path = baseDir + "/" + config.getSubmissionId() + "/";
        String executableFilePath = path + config.getCompile().getExeName();
        String[] testCase = config.getTestCase();

        List<MessageHandler.JudgerResultResponse> list = new ArrayList<>();
        for (int i = 0; i < testCase.length; i++) {
            File file = FileUtil.touch(String.format("%s%d.in", path, i));
            FileWriter writer = new FileWriter(file);
            writer.write(testCase[i]);
            String output = String.format("%s%d.out", path, i);
            JudgerConfig judgerConfig = JudgerConfig.builder()
                    .maxCpuTime(config.getCompile().getMaxCpuTime())
                    .maxRealTime(config.getCompile().getMaxRealTime())
                    .maxMemory(config.getCompile().getMaxMemory())
                    .maxStack(128 * 1024 * 1024)
                    .maxOutputSize(20 * 1024 * 1024)
                    .maxProcessNumber(-1)
                    .memoryLimitCheckOnly(config.getRun().getMemoryLimitCheckOnly())
                    .exePath(executableFilePath)
                    .inputPath(file.getPath())
                    .outputPath(output)
                    .errorPath(path + "err.out")
                    .args(null)
                    .env(null)
                    .logPath(judgerRunLogPath)
                    .seccompRule(config.getRun().getSeccompRule())
                    .uid(0)
                    .gid(0).build();
            JudgerResult result = JudgerCore.getInstance().run(judgerConfig);
            MessageHandler.JudgerResultResponse response = new MessageHandler.JudgerResultResponse();
            response.setValue(SecureUtil.md5(FileUtil.file(output)));
            BeanUtils.copyProperties(result, response);
            list.add(response);
        }
        return list;
    }
}
