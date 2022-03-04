package com.itheima.system.exception;

public class BusinessException extends RuntimeException {
    //自定义异常中封装对应的错误编码，用于异常处理时获取对应的操作编码
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException(Integer code) {
        this.code = code;
    }

    public BusinessException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause,Integer code) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(Throwable cause,Integer code) {
        super(cause);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,Integer code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
