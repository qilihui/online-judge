package com.github.qilihui.oj.judge.server.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import com.github.qilihui.oj.judge.core.JudgerCore;
import com.github.qilihui.oj.judge.core.enums.SeccompRuleEnum;
import com.github.qilihui.oj.judge.core.model.JudgerConfig;
import com.github.qilihui.oj.judge.core.model.JudgerResult;
import com.github.qilihui.oj.judge.server.config.LangConfig;
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
public class JudgeCompiler {
    @Value("${judger.baseDir}")
    private String baseDir;

    @Value("${judger.compilerLogPath}")
    private String compilerLogPath;

    /**
     * 编译代码
     */
    public JudgerResult compile(LangConfig config) {
        String path = baseDir + "/" + config.getSubmissionId() + "/";
        LangConfig.CompileDTO compileConfig = config.getCompile();
        for (int i = 0; i < compileConfig.getCompileCommand().length; i++) {
            if ("{src_path}".equals(compileConfig.getCompileCommand()[i])) {
                compileConfig.getCompileCommand()[i] = path + compileConfig.getSrcName();
            }
            if ("{exe_path}".equals(compileConfig.getCompileCommand()[i])) {
                compileConfig.getCompileCommand()[i] = path + compileConfig.getExeName();
            }
        }
        String outPath = path + "compiler.out";
        File file = FileUtil.touch(path + compileConfig.getSrcName());
        FileWriter writer = new FileWriter(file);
        writer.write(config.getSrc());
        JudgerConfig judgerConfig = JudgerConfig.builder()
                .maxCpuTime(compileConfig.getMaxCpuTime())
                .maxRealTime(compileConfig.getMaxRealTime())
                .maxMemory(compileConfig.getMaxMemory())
                .maxStack(128 * 1024 * 1024)
                .maxOutputSize(20 * 1024 * 1024)
                .maxProcessNumber(-1)
                .memoryLimitCheckOnly(0)
                .exePath(compileConfig.getCompileCommand()[0])
                .inputPath("/dev/null")
                .outputPath(outPath)
                .errorPath(outPath)
                .args(compileConfig.getCompileCommand())
                .env(new String[]{"PATH=" + System.getenv("PATH")})
                .logPath(compilerLogPath)
                .seccompRule(SeccompRuleEnum.NON)
                .uid(0)
                .gid(0).build();
        return JudgerCore.getInstance().run(judgerConfig);
    }
}
