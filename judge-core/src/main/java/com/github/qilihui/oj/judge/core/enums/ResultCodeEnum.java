package com.github.qilihui.oj.judge.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JudgerResult.result
 *
 * @author qilihui
 * @date 2021/12/1 12:09 上午
 */
@AllArgsConstructor
@Getter
public enum ResultCodeEnum {
    SUCCESS(0),
    CPU_TIME_LIMIT_EXCEEDED(1),
    REAL_TIME_LIMIT_EXCEEDED(2),
    MEMORY_LIMIT_EXCEEDED(3),
    RUNTIME_ERROR(4),
    SYSTEM_ERROR(5);

    private final int code;
}
