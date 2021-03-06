package com.github.qilihui.oj.judge.core.model;

import lombok.Data;

/**
 * Do not modify it at will, otherwise judger will not work
 *
 * @author qilihui
 * @date 2021/11/28 10:33 上午
 */
@Data
public class JudgerResult {
    int cpuTime;
    int realTime;
    long memory;
    int signal;
    int exitCode;
    int error;
    int result;
}
