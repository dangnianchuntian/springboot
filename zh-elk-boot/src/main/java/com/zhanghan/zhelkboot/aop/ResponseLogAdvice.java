/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：ResponseLogAdvice.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhelkboot.aop;

import com.zhanghan.zhelkboot.util.FileBeatLogUtil;
import com.zhanghan.zhelkboot.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ResponseLogAdvice implements ResponseBodyAdvice {

    @Autowired
    private Environment env;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            if (o != null) {

                Logger log = LoggerFactory.getLogger("logstashInfo");

                String applicationName = env.getProperty("spring.application.name");

                FileBeatLogUtil.writeRequestInfo(methodParameter, mediaType, request);

                String responseParams = JsonUtil.objtoJson(o);

                String reqName = methodParameter.getDeclaringClass().getName() + "." + methodParameter.getMember().getName();

                FileBeatLogUtil.writeLog(log, applicationName, reqName, responseParams.toString());

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return o;
    }


}