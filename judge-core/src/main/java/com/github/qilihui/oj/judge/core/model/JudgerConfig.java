package com.github.qilihui.oj.judge.core.model;

import com.github.qilihui.oj.judge.core.enums.SeccompRuleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author qilihui
 * @date 2021/11/28 10:28 上午
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    SeccompRuleEnum seccompRule;
    int uid;
    int gid;
}
