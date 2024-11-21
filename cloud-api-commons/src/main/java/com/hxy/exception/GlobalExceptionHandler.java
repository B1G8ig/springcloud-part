package com.hxy.exception;

import com.hxy.utils.Result;
import com.hxy.utils.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> RuntimeExceptionHandler(Exception e){
        System.out.println("######come in GlobalExceptionHandler");
        log.error("全局异常信息:{}",e.getMessage(),e);
        return Result.build(null, ResultCodeEnum.RC500.getCode(), e.getMessage());
    }
}
