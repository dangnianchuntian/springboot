/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战:签到奖励实现方案
 * 类名称：GlobalExceptionHandler.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhsignin.filter;


import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zhanghan.zhsignin.util.wrapper.WrapMapper;
import com.zhanghan.zhsignin.util.wrapper.Wrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局的的异常拦截器
 *
 * @author https://zhanghan.blog.csdn.net
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * IllegalArgumentException
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper illegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException={}", e.getMessage(), e);
        return WrapMapper.error("param error");
    }

    /**
     * IllegalStateException
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper IllegalStateException(IllegalStateException e) {
        log.error("IllegalStateException={}", e.getMessage(), e);
        return WrapMapper.error();
    }

    /**
     * HttpMessageNotWritableException
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(HttpMessageNotWritableException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper HttpMessageNotWritableException(HttpMessageNotWritableException e) {
        log.error("HttpMessageNotWritableException={}", e.getMessage(), e);
        return WrapMapper.error();
    }


    /**
     * HttpMessageNotReadableException
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException={}", e.getMessage(), e);
        return WrapMapper.error();
    }

    /**
     * ConversionFailedException
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper ConversionFailedException(ConversionFailedException e) {
        log.error("ConversionFailedException={}", e.getMessage(), e);
        return WrapMapper.error();
    }

    /**
     * BindException
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper BindException(BindException e) {
        log.error("BindException={}", e.getMessage(), e);
        return WrapMapper.error();
    }

    /**
     * MethodArgumentNotValidException
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper validationError(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException={}", e.getMessage(), e);
        return WrapMapper.error();
    }


    /**
     * Exception
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper exception(Exception e) {
        log.error("Exception={}", e.getMessage(), e);
        return WrapMapper.error();
    }
}
