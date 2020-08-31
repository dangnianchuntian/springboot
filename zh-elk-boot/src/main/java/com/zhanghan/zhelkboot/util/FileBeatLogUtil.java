/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：FileBeatLogUtil.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhelkboot.util;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;

public class FileBeatLogUtil {

    public static void writeRequestInfo(MethodParameter methodParameter, MediaType mediaType, HttpServletRequest request) {

        String reqName = methodParameter.getDeclaringClass().getName() + "." + methodParameter.getMethod().getName();
        String applicationName = "111";
        String requestURI = request.getRequestURI();

        String requestParams = FileBeatLogUtil.getParams(request);

        /**
         * 防止MDC值空指针，所有入参不为null
         */
        applicationName = org.springframework.util.StringUtils.isEmpty(applicationName) ? "" : applicationName;
        requestURI = org.springframework.util.StringUtils.isEmpty(requestURI) ? "" : requestURI;
        reqName = org.springframework.util.StringUtils.isEmpty(reqName) ? "" : reqName;
        requestParams = "null".equals(requestParams) ? "" : requestParams;
        /**
         * MDC值为ES键值对JSON信息
         */
        MDC.put("applicationName", applicationName);
        MDC.put("requestURI", requestURI);
        MDC.put("sourceName", reqName);
        MDC.put("requestParams", requestParams);
        MDC.put("responseHeader", "");
        MDC.put("responseCode", "");
        MDC.put("responseMsg", "");
        MDC.put("responseBody", "");
        MDC.put("responseTime", "");
        MDC.put("exception", "");
    }

    public static void writeLog(Logger log, String applicationName, String reqName, String params) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestURI = request.getRequestURI();


        /**
         * 防止MDC值空指针，所有入参不为null
         */
        applicationName = StringUtils.isEmpty(applicationName) ? "" : applicationName;
        requestURI = StringUtils.isEmpty(requestURI) ? "" : requestURI;
        reqName = StringUtils.isEmpty(reqName) ? "" : reqName;
        params = "null".equals(params) ? "" : params;
        /**
         * map值为ES备份字符串信息(此字符串不会被ES解析为JSON字符串)
         */
        LinkedHashMap<String, Object> reqInfo = new LinkedHashMap<>();
        reqInfo.put("applicationName", applicationName);
        reqInfo.put("requestURI", requestURI);
        reqInfo.put("sourceName", reqName);

        reqInfo.put("httpParams", params);
        /**
         * MDC值为ES键值对JSON信息
         */
        MDC.put("responseCode", "code");
        MDC.put("responseMsg", "");
        MDC.put("responseBody", params);
        MDC.put("responseTime", getStringTodayTime());
        String reqInfoJsonStr = JSON.toJSONString(reqInfo);
        log.info(reqInfoJsonStr);

    }

    /**
     * 获取请求参数，处理为json字符串
     *
     * @param joinPoint
     * @return
     */
    public static String getParams(JoinPoint joinPoint) {
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

    public static String getParams(HttpServletRequest request) {

        Enumeration<String> argNames = request.getParameterNames();
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        if (null == argNames) {
            return JSON.toJSONString(linkedHashMap);
        }

        while (argNames.hasMoreElements()) {
            String thisArgName = argNames.nextElement();
            String thisArgValue = request.getParameter(thisArgName);
            linkedHashMap.put(thisArgName, thisArgValue);
        }

        return JSON.toJSONString(linkedHashMap);
    }

    public static String getStringTodayTime() {
        Date todat_date = new Date();
        //将日期格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //转换成字符串格式
        return simpleDateFormat.format(todat_date);
    }
}
