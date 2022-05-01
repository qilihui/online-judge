package com.github.qilihui.oj.judge.server.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.crypto.SecureUtil;
import com.github.qilihui.oj.judge.core.JudgerCore;
import com.github.qilihui.oj.judge.core.enums.SeccompRuleEnum;
import com.github.qilihui.oj.judge.core.model.JudgerConfig;
import com.github.qilihui.oj.judge.core.model.JudgerResult;
import com.github.qilihui.oj.judge.server.config.JudgerRequest;
import com.github.qilihui.oj.judge.server.config.JudgerResponse;
import lombok.extern.slf4j.Slf4j;
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

    public List<JudgerResponse.RunDTO> execute(JudgerRequest config) {
        String path = baseDir + "/" + config.getSubmissionId() + "/";
        String executableFilePath = path + config.getCompile().getExeName();
        List<JudgerResponse.RunDTO> list = new ArrayList<>();
        for (JudgerRequest.TestCaseDTO testCaseDTO : config.getTestCase()) {
            File file = FileUtil.touch(String.format("%s%d.in", path, testCaseDTO.getId()));
            FileWriter writer = new FileWriter(file);
            writer.write(testCaseDTO.getIn());
            String output = String.format("%s%d.out", path, testCaseDTO.getId());
            JudgerConfig judgerConfig = JudgerConfig.builder()
                    .maxCpuTime(config.getMaxCpuTime())
                    .maxRealTime(config.getMaxRealTime())
                    .maxMemory(config.getMaxMemory())
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
                    .seccompRule(SeccompRuleEnum.findByRuleName(config.getRun().getSeccompRule()))
                    .uid(0)
                    .gid(0).build();

            JudgerResult result = JudgerCore.getInstance().run(judgerConfig);
            JudgerResponse.RunDTO dto = new JudgerResponse.RunDTO();
            dto.setId(testCaseDTO.getId());
            dto.setJudgerResult(result);
            dto.setMd5(SecureUtil.md5(FileUtil.file(output)));
            dto.setPass(dto.getMd5().equals(testCaseDTO.getMd5()));
            list.add(dto);

            if (!dto.getPass()) {
                break;
            }
        }
        return list;
    }
}
