/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：ControllerLogAspectConf.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

@Aspect
@Order(0)
@Component
public class ControllerLogAspectConf {


    @Autowired
    private Environment env;

    /**
     * 范围切点方法
     */
    @Pointcut("execution(* com.zhanghan.zhboot.controller..*.*(..))")
    public void methodPointCut() {
    }

    @Before("methodPointCut()")
    void doBefore(JoinPoint joinPoint) {
        authLogic(joinPoint);
    }

    /**
     * 认证逻辑
     *
     * @param joinPoint
     * @throws Exception
     */
    private void authLogic(JoinPoint joinPoint) {
        Logger log = LoggerFactory.getLogger("logstashInfo");
        //获取当前http请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //操作时间
        String actionTime = getStringTodayTime();

        String reqName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String applicationName = env.getProperty("spring.application.name");
        String requestURI = request.getRequestURI();
        String requestParams = getParams(joinPoint);
        /**
         * 防止MDC值空指针，所有入参不为null
         */
        applicationName = StringUtils.isEmpty(applicationName) ? "" : applicationName;
        requestURI = StringUtils.isEmpty(requestURI) ? "" : requestURI;
        reqName = StringUtils.isEmpty(reqName) ? "" : reqName;
        requestParams = "null".equals(requestParams) ? "" : requestParams;
        actionTime = StringUtils.isEmpty(actionTime) ? "" : actionTime;
        /**
         * map值为ES备份字符串信息(此字符串不会被ES解析为JSON字符串)
         */
        LinkedHashMap<String, Object> reqInfo = new LinkedHashMap<>();
        reqInfo.put("applicationName", applicationName);
        reqInfo.put("requestURI", requestURI);
        reqInfo.put("sourceName", reqName);
        reqInfo.put("requestParams", requestParams);
        reqInfo.put("requestTime", actionTime);
        /**
         * MDC值为ES键值对JSON信息
         */
        MDC.put("applicationName", applicationName);
        MDC.put("requestURI", requestURI);
//        MDC.put("requestIp", actionIp);
        MDC.put("sourceName", reqName);
        MDC.put("requestParams", requestParams);
        MDC.put("requestTime", actionTime);
        String reqInfoJsonStr = JSON.toJSONString(reqInfo);
        log.info(reqInfoJsonStr);

    }

    /**
     * 获取请求参数，处理为json字符串
     *
     * @param joinPoint
     * @return
     */
    private String getParams(JoinPoint joinPoint) {
        Object[] argValues = joinPoint.getArgs();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        if (argNames != null && argNames.length > 0) {
            for (int i = 0; i < argNames.length; i++) {
                String thisArgName = argNames[i];
                String thisArgValue = argValues[i].toString();
                linkedHashMap.put(thisArgName, thisArgValue);
            }
        }
        return JSON.toJSONString(linkedHashMap);
    }

    public static String getStringTodayTime() {
        Date todat_date = new Date();
        //将日期格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //转换成字符串格式
        return simpleDateFormat.format(todat_date);
    }

}
