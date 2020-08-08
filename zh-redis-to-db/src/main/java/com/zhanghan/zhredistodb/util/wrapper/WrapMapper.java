/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战解决高并发数据入库: Redis 缓存+MySQL 批量入库
 * 类名称：RedisConfig.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhredistodb.util.wrapper;

import org.springframework.util.StringUtils;

/**
 * The class Wrap mapper.
 *
 * @author https://zhanghan.blog.csdn.net
 */
public class WrapMapper {

    /**
     * Instantiates a new wrap mapper.
     */
    private WrapMapper() {
    }

    /**
     * Wrap.
     *
     * @param <E>     the element type
     * @param code    the code
     * @param message the message
     * @param o       the o
     *
     * @return the wrapper
     */
    public static <E> Wrapper<E> wrap(int code, String message, E o) {
        return new Wrapper<>(code, message, o);
    }

    /**
     * Wrap.
     *
     * @param <E>     the element type
     * @param code    the code
     * @param message the message
     *
     * @return the wrapper
     */
    public static <E> Wrapper<E> wrap(int code, String message) {
        return wrap(code, message, null);
    }

    /**
     * Wrap.
     *
     * @param <E>  the element type
     * @param code the code
     *
     * @return the wrapper
     */
    public static <E> Wrapper<E> wrap(int code) {
        return wrap(code, null);
    }

    /**
     * Wrap.
     *
     * @param <E> the element type
     * @param e   the e
     *
     * @return the wrapper
     */
    public static <E> Wrapper<E> wrap(Exception e) {
        return new Wrapper<>(Wrapper.ERROR_CODE, e.getMessage(), null);
    }

    /**
     * Un wrapper.
     *
     * @param <E>     the element type
     * @param wrapper the wrapper
     *
     * @return the e
     */
    public static <E> E unWrap(Wrapper<E> wrapper) {
        return wrapper.getResult();
    }

    /**
     * Wrap ERROR. code=1
     *
     * @param <E> the element type
     *
     * @return the wrapper
     */
    public static <E> Wrapper<E> error() {
        return wrap(Wrapper.ERROR_CODE, Wrapper.ERROR_MESSAGE, null);
    }

    /**
     * Error wrapper.
     *
     * @param <E>     the type parameter
     * @param message the message
     *
     * @return the wrapper
     */
    public static <E> Wrapper<E> error(String message) {
        return wrap(Wrapper.ERROR_CODE, StringUtils.isEmpty(message) ? Wrapper.ERROR_MESSAGE : message, null);
    }

    /**
     * Wrap SUCCESS. code=0
     *
     * @param <E> the element type
     *
     * @return the wrapper
     */
    public static <E> Wrapper<E> ok() {
        return new Wrapper<>();
    }

    /**
     * Ok wrapper.
     *
     * @param <E> the type parameter
     * @param o   the o
     *
     * @return the wrapper
     */
    public static <E> Wrapper<E> ok(E o) {
        return new Wrapper<>(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, o);
    }
}
