package com.github.qilihui.oj.judge.server.config;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qilihui
 * @date 2021/12/5 11:28 下午
 */
@NoArgsConstructor
@Data
public class JudgerRequest {
    private String language;
    private String submissionId;
    private String src;
    private Integer maxCpuTime;
    private Integer maxRealTime;
    private Long maxMemory;
    private CompileDTO compile;
    private RunDTO run;
    private List<TestCaseDTO> testCase;

    @NoArgsConstructor
    @Data
    public static class CompileDTO {
        private String srcName;
        private String exeName;
        private String[] compileCommand;
    }

    @NoArgsConstructor
    @Data
    public static class RunDTO {
        private String[] command;
        private String seccompRule;
        private Object env;
        private Integer memoryLimitCheckOnly;
    }

    @NoArgsConstructor
    @Data
    public static class TestCaseDTO {
        private Integer id;
        private String in;
        private String md5;
    }
}
