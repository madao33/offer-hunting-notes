package com.itheima._09自定义异常;

/**
    自定义编译时异常类：
        1.继承Exception。
        2.重写构造器。
 */
public class ItheimaAgeIllegalException extends Exception {
    public ItheimaAgeIllegalException() {
    }

    public ItheimaAgeIllegalException(String message) {
        super(message);
    }

    public ItheimaAgeIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItheimaAgeIllegalException(Throwable cause) {
        super(cause);
    }

    public ItheimaAgeIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
