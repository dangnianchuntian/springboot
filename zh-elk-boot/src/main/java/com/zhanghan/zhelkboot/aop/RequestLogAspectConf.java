/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：ControllerLogAspectConf.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhelkboot.aop;

import com.zhanghan.zhelkboot.util.FileBeatLogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Aspect
@Order(0)
@Component
public class RequestLogAspectConf {


    @Autowired
    private Environment env;

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
            Logger log = LoggerFactory.getLogger("logstashInfo");

            String applicationName = env.getProperty("spring.application.name");

            //获取当前http请求
            String reqName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

            String requestParams = FileBeatLogUtil.getParams(joinPoint);

            FileBeatLogUtil.writeLog(log, applicationName, reqName, requestParams);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
