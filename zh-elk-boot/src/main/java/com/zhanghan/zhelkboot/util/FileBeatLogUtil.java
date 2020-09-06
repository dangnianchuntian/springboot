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
import com.zhanghan.zhelkboot.util.wrapper.Wrapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileBeatLogUtil {

    public static void writeRequestInfo(HttpServletRequest request, String applicationName, String reqName, String requestParams) {

        String requestURI = request.getRequestURI();

        //获取requestHeader
        Enumeration<String> requestHeaderNames = request.getHeaderNames();
        Map<String, Object> reuestHeaderMap = new HashMap<>();
        while (requestHeaderNames.hasMoreElements()) {
            String name = requestHeaderNames.nextElement();
            String value = request.getHeaders(name).nextElement();
            reuestHeaderMap.put(name, value);
        }
        String requestHeader = "";
        if (null != reuestHeaderMap && reuestHeaderMap.size() > 0) {
            requestHeader = JSON.toJSONString(reuestHeaderMap);
        }

        //防止MDC值空指针，所有入参不为null
        applicationName = org.springframework.util.StringUtils.isEmpty(applicationName) ? "" : applicationName;
        requestURI = org.springframework.util.StringUtils.isEmpty(requestURI) ? "" : requestURI;
        reqName = org.springframework.util.StringUtils.isEmpty(reqName) ? "" : reqName;
        requestParams = "null".equals(requestParams) ? "" : requestParams;

        //MDC值为ES键值对JSON信息
        MDC.put("applicationName", applicationName);
        MDC.put("requestTime", getStringTodayTime());
        MDC.put("requestURI", requestURI);
        MDC.put("requestHeader", requestHeader);
        MDC.put("sourceName", reqName);
        MDC.put("requestParams", requestParams);
    }

    public static void writeResponseLog(Object o, Logger log, HttpServletResponse response) {

        //取responseHeader内容
        Map<String, Object> responseHeaderMap = new HashMap<>();
        Collection<String> headerNames = response.getHeaderNames();
        headerNames.forEach(name -> {
            responseHeaderMap.put(name, response.getHeader(name));
        });
        String strResponseHeader = "";
        if (null != responseHeaderMap && responseHeaderMap.size() > 0) {
            strResponseHeader = JSON.toJSONString(responseHeaderMap);
        }

        //获取response内容
        String responseCode = "";
        String responseMsg = "";
        String responseBody = "";
        Wrapper wrapper;
        if (null != o) {
            wrapper = (Wrapper) o;
            if (null != wrapper) {
                responseCode = String.valueOf(wrapper.getCode());
                responseMsg = wrapper.getMessage();
                responseBody = wrapper.getResult().toString();
            }
        }


        //MDC值为ES键值对JSON信息
        MDC.put("responseHeader", strResponseHeader);
        MDC.put("responseCode", responseCode);
        MDC.put("responseMsg", responseMsg);
        MDC.put("responseBody", responseBody);
        MDC.put("responseTime", getStringTodayTime());

        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        String reqInfoJsonStr = JSON.toJSONString(copyOfContextMap);
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //转换成字符串格式
        return simpleDateFormat.format(todat_date);
    }
}
