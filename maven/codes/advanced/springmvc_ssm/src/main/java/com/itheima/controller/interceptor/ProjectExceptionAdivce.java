package com.itheima.controller.interceptor;

import com.itheima.controller.results.Result;
import com.itheima.system.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@ControllerAdvice
public class ProjectExceptionAdivce {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    //对出现异常的情况进行拦截，并将其处理成统一的页面数据结果格式
    public Result doBusinessException(BusinessException e){
        return new Result(e.getCode(),e.getMessage());
    }

}
