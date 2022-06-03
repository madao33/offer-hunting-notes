package com.itheima._09自定义异常;

/**
    自定义运行时异常类：
        1.继承RuntimeException
        2.重写构造器。
 */
public class ItheimaAgeIllegalRuntimeException extends RuntimeException {
    public ItheimaAgeIllegalRuntimeException() {
    }

    public ItheimaAgeIllegalRuntimeException(String message) {
        super(message);
    }

    public ItheimaAgeIllegalRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItheimaAgeIllegalRuntimeException(Throwable cause) {
        super(cause);
    }

    public ItheimaAgeIllegalRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
