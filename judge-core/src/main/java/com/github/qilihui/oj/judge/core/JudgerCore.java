package com.github.qilihui.oj.judge.core;

import com.github.qilihui.oj.judge.core.model.JudgerConfig;
import com.github.qilihui.oj.judge.core.model.JudgerResult;

/**
 * @author qilihui
 * @date 2021/11/28 12:48 上午
 */
public class JudgerCore {
    static {
        System.loadLibrary("judger");
    }

    private static volatile JudgerCore instance;

    private JudgerCore() {
    }

    public static JudgerCore getInstance() {
        if (instance == null) {
            synchronized (JudgerCore.class) {
                if (instance == null) {
                    instance = new JudgerCore();
                }
            }
        }
        return instance;
    }

    public JudgerResult run(JudgerConfig config) {
        return run(config.getMaxCpuTime(), config.getMaxRealTime(), config.getMaxMemory(), config.getMaxStack(),
                config.getMaxProcessNumber(), config.getMaxOutputSize(), config.getMemoryLimitCheckOnly(),
                config.getExePath(), config.getInputPath(), config.getOutputPath(), config.getErrorPath(),
                config.getArgs(), config.getArgs() == null ? 0 : config.getArgs().length, config.getEnv(), config.getEnv() == null ? 0 : config.getEnv().length,
                config.getLogPath(), config.getSeccompRule().getRuleName(),
                config.getUid(), config.getGid());
    }

    public native JudgerResult run(int maxCpuTime, int maxRealTime, long maxMemory, long maxStack,
                                   int maxProcessNumber, long maxOutputSize, int memoryLimitCheckOnly,
                                   String exePath, String inputPath, String outputPath, String errorPath,
                                   String[] args, int argsLength, String[] env, int envLength,
                                   String logPath, String seccompRuleName,
                                   int uid, int gid);
}
