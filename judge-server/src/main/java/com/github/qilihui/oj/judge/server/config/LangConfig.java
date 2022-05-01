package com.github.qilihui.oj.judge.server.config;

import com.github.qilihui.oj.judge.core.enums.SeccompRuleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qilihui
 * @date 2021/12/5 11:28 下午
 */
@NoArgsConstructor
@Data
public class LangConfig {
    private String language;
    private String src;
    private String submissionId;
    private LangConfig.CompileDTO compile;
    private LangConfig.RunDTO run;
    private String[] testCase;

    @NoArgsConstructor
    @Data
    public static class CompileDTO {
        private String srcName;
        private String exeName;
        private Integer maxCpuTime;
        private Integer maxRealTime;
        private Long maxMemory;
        private String[] compileCommand;
    }

    @NoArgsConstructor
    @Data
    public static class RunDTO {
        private String[] command;
        private SeccompRuleEnum seccompRule;
        private String env;
        private Integer memoryLimitCheckOnly;
    }
}
