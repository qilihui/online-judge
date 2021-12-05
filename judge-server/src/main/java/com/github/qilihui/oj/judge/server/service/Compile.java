package com.github.qilihui.oj.judge.server.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import com.github.qilihui.oj.judge.core.JudgerCore;
import com.github.qilihui.oj.judge.core.enums.ResultCodeEnum;
import com.github.qilihui.oj.judge.core.enums.SeccompRuleEnum;
import com.github.qilihui.oj.judge.core.model.JudgerConfig;
import com.github.qilihui.oj.judge.core.model.JudgerResult;
import com.github.qilihui.oj.judge.server.config.LangConfig;
import com.github.qilihui.oj.judge.server.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author qilihui
 * @date 2021/12/5 10:33 下午
 */
@Slf4j
@Service
public class Compile {
    @Value("${judger.baseDir}")
    private String baseDir;

    @Value("${judger.compilerLogPath}")
    private String compilerLogPath;

    /**
     * 编译代码
     *
     * @param config       编译配置
     * @param submissionId 提交id
     * @param src          源码
     * @return 执行路径
     */
    public String compile(LangConfig.CompileDTO config, Integer submissionId, String src) throws BizException {
        String path = baseDir + submissionId;
        String compileFilePath = path + config.getSrcName();
        String outPath = path + "compiler.out";
        File file = FileUtil.touch(path);
        FileWriter writer = new FileWriter(file);
        writer.write(src);
        JudgerConfig judgerConfig = JudgerConfig.builder()
                .maxCpuTime(config.getMaxCpuTime())
                .maxRealTime(config.getMaxRealTime())
                .maxMemory(config.getMaxMemory())
                .maxStack(128 * 1024 * 1024)
                .maxOutputSize(20 * 1024 * 1024)
                .maxProcessNumber(-1)
                .memoryLimitCheckOnly(0)
                .exePath(config.getCompileCommand()[0])
                .inputPath("/dev/null")
                .outputPath(outPath)
                .errorPath(outPath)
                .args(config.getCompileCommand())
                .env(new String[]{"PATH=" + System.getenv("PATH")})
                .logPath(compilerLogPath)
                .seccompRule(SeccompRuleEnum.NON)
                .uid(0)
                .gid(0).build();
        JudgerResult judgerResult = JudgerCore.getInstance().run(judgerConfig);
        if (judgerResult.getResult() != ResultCodeEnum.SUCCESS.getCode()) {
            throw new BizException("");
        }
        return "";
    }
}
