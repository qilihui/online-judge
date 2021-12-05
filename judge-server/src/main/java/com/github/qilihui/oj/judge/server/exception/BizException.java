package com.github.qilihui.oj.judge.server.exception;

/**
 * 业务异常
 *
 * @author qilihui
 * @date 2021/12/5 11:12 下午
 */
public class BizException extends Exception {
    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
