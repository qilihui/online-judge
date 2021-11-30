package com.github.qilihui.oj.core;

import com.github.qilihui.oj.core.model.JudgerConfig;
import com.github.qilihui.oj.core.model.JudgerResult;

/**
 * @author qilihui
 * @date 2021/11/28 12:48 上午
 */
public class JudgerCore {
    static {
        System.loadLibrary("judger");
    }

    public JudgerResult run(JudgerConfig config) {
        return run(config.getMaxCpuTime(), config.getMaxRealTime(), config.getMaxMemory(), config.getMaxStack(),
                config.getMaxProcessNumber(), config.getMaxOutputSize(), config.getMemoryLimitCheckOnly(),
                config.getExePath(), config.getInputPath(), config.getOutputPath(), config.getErrorPath(),
                config.getArgs(), config.getArgs() == null ? 0 : config.getArgs().length, config.getEnv(), config.getEnv() == null ? 0 : config.getEnv().length,
                config.getLogPath(), config.getSeccompRuleName(),
                config.getUid(), config.getGid());
    }

    public native JudgerResult run(int maxCpuTime, int maxRealTime, long maxMemory, long maxStack,
                                   int maxProcessNumber, long maxOutputSize, int memoryLimitCheckOnly,
                                   String exePath, String inputPath, String outputPath, String errorPath,
                                   String[] args, int argsLength, String[] env, int envLength,
                                   String logPath, String seccompRuleName,
                                   int uid, int gid);
}
