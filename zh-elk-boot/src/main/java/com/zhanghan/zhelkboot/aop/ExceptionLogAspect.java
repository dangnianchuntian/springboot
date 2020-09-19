/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：SpringBoot项目接入ELK
 * 类名称：ExceptionLogAspect.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhelkboot.aop;

import com.zhanghan.zhelkboot.util.FileBeatLogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Order(1)
@Component
public class ExceptionLogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 范围切点方法
     */
    @Pointcut("execution(* com.zhanghan.zhelkboot..*.*(..))")
    public void methodPointCut() {
    }

    @AfterThrowing(throwing = "ex", pointcut = "methodPointCut()")
    public void throwss(JoinPoint joinPoint, Exception ex) {
        try {
            String methodArgs = Arrays.toString(joinPoint.getArgs());
            FileBeatLogUtil.writeExceptionLog(joinPoint.getSignature().toString(), methodArgs, ex.getMessage());
        } catch (Exception e) {
            logger.error("ExceptionLogAspect;writeExceptionLog;Exception:{}", e.getMessage());
        }
    }
}
