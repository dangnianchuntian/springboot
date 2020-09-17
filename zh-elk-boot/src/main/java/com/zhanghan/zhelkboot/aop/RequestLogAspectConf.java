/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：SpringBoot项目接入ELK
 * 类名称：RequestLogAspectConf.java
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
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Order(0)
@Component
public class RequestLogAspectConf {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private Environment env;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 范围切点方法
     */
    @Pointcut("execution(* com.zhanghan.zhelkboot.controller..*.*(..))")
    public void methodPointCut() {
    }

    @Before("methodPointCut()")
    void doBefore(JoinPoint joinPoint) {
        authLogic(joinPoint);
    }

    private void authLogic(JoinPoint joinPoint) {

        try {

            String applicationName = env.getProperty("spring.application.name");

            //获取当前http请求
            String reqName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

            String requestParams = FileBeatLogUtil.getParams(joinPoint);

            FileBeatLogUtil.writeRequestInfo(request, applicationName, reqName, requestParams);
        } catch (Exception e) {
            logger.error("RequestLogAspectConf;authLogic;Exception:{}", e.getMessage());
        }

    }

    @AfterThrowing(throwing = "ex", pointcut = "methodPointCut()")
    public void throwss(JoinPoint joinPoint, Exception ex) {
        try {
            String methodArgs = Arrays.toString(joinPoint.getArgs());
            FileBeatLogUtil.writeExceptionLog(false, getClassAndMethodName(joinPoint), methodArgs, ex.getMessage());
        } catch (Exception e) {
            logger.error("RequestLogAspectConf;writeExceptionLog;Exception:{}", e.getMessage());
        }
    }

    private String getClassAndMethodName(JoinPoint joinPoint) {
        String classAndMethodName = joinPoint.toShortString().substring(10);
        classAndMethodName = classAndMethodName.substring(0, classAndMethodName.length() - 1);
        return classAndMethodName;
    }

}
