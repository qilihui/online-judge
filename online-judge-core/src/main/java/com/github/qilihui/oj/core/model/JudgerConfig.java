package com.github.qilihui.oj.core.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author qilihui
 * @date 2021/11/28 10:28 上午
 */
@Data
@Accessors(chain = true)
@Builder
public class JudgerConfig {
    int maxCpuTime;
    int maxRealTime;
    long maxMemory;
    long maxStack;
    int maxProcessNumber;
    long maxOutputSize;
    int memoryLimitCheckOnly;
    String exePath;
    String inputPath;
    String outputPath;
    String errorPath;
    String[] args;
    String[] env;
    String logPath;
    String seccompRuleName;
    int uid;
    int gid;
}
