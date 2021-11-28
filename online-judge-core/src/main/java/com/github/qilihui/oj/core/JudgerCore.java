package com.github.qilihui.oj.core;

import com.github.qilihui.oj.core.model.JudgerResult;

/**
 * @author qilihui
 * @date 2021/11/28 12:48 上午
 */
public class JudgerCore {
    public native JudgerResult run(
            int maxCpuTime,
            int maxRealTime,
            long maxMemory,
            long maxStack,
            int maxProcessNumber,
            long maxOutputSize,
            int memoryLimitCheckOnly,
            String exePath,
            String inputPath,
            String outputPath,
            String errorPath,
            String[] args,
            int argsLength,
            String[] env,
            int envLength,
            String logPath,
            String seccompRuleName,
            int uid,
            int gid
    );
}
