package com.github.qilihui.oj.judge.server.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qilihui
 * @date 2022/5/1 10:32 下午
 */
@AllArgsConstructor
@Getter
public enum ResponseCode {
    SUCCESS(0, "SUCCESS"),
    P_1000001(1000001, "执行异常"),
    

    ;
    private Integer code;
    private String msg;
}
