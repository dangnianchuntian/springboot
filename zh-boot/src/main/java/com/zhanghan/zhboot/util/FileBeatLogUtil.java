/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：FileBeatLogUtil.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.util;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.UUID;

public class FileBeatLogUtil {

    public static void writeLog(Logger log, String applicationName, String type, String reqName, String params) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestURI = request.getRequestURI();

        String httpUUID = "";

        if (type.equals(HttpTypeUtil.REQUEST)) {
            httpUUID = UUID.randomUUID().toString();
            request.setAttribute("uuid", httpUUID);
        } else {
            if (!ObjectUtils.isEmpty( request.getAttribute("uuid"))) {
                httpUUID = request.getAttribute("uuid").toString();
            }
        }

        //请求时间
        String actionTime = getStringTodayTime();

        /**
         * 防止MDC值空指针，所有入参不为null
         */
        applicationName = StringUtils.isEmpty(applicationName) ? "" : applicationName;
        requestURI = StringUtils.isEmpty(requestURI) ? "" : requestURI;
        reqName = StringUtils.isEmpty(reqName) ? "" : reqName;
        params = "null".equals(params) ? "" : params;
        actionTime = StringUtils.isEmpty(actionTime) ? "" : actionTime;
        /**
         * map值为ES备份字符串信息(此字符串不会被ES解析为JSON字符串)
         */
        LinkedHashMap<String, Object> reqInfo = new LinkedHashMap<>();
        reqInfo.put("applicationName", applicationName);
        reqInfo.put("requestURI", requestURI);
        reqInfo.put("sourceName", reqName);
        reqInfo.put("httpUUID", httpUUID);
        reqInfo.put("httpType", type);
        reqInfo.put("httpParams", params);
        reqInfo.put("httpTime", actionTime);
        /**
         * MDC值为ES键值对JSON信息
         */
        MDC.put("applicationName", applicationName);
        MDC.put("requestURI", requestURI);
        MDC.put("sourceName", reqName);
        MDC.put("httpUUID", httpUUID);
        MDC.put("httpType", type);
        MDC.put("httpParams", params);
        MDC.put("httpTime", actionTime);
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

    public static String getStringTodayTime() {
        Date todat_date = new Date();
        //将日期格式化
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //转换成字符串格式
        return simpleDateFormat.format(todat_date);
    }
}
