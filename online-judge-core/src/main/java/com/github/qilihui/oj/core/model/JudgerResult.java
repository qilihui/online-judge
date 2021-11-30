package com.github.qilihui.oj.core.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author qilihui
 * @date 2021/11/28 10:33 上午
 */
@Data
@Accessors(chain = true)
@Builder
public class JudgerResult {
    int cpuTime;
    int realTime;
    long memory;
    int signal;
    int exitCode;
    int error;
    int result;
}
