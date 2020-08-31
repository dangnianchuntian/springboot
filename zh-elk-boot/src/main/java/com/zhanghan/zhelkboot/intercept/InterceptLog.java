/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：InterceptLog.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhelkboot.intercept;

import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

@Component
@Order(10000)
public class InterceptLog implements HandlerInterceptor {

    //日志操作
    private static final Logger log = LoggerFactory.getLogger(InterceptLog.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        beforeRequestLogInfo(request);
        return true;
    }

    //记录请求日志
    private void beforeRequestLogInfo(HttpServletRequest request) {
        ///获取请求方式
        String method = request.getMethod();
        ///请求地址
        String url = request.getRequestURI();
        ///获取请求参数
        String paramsStr = "";
        if ("POST".equals(method)) {
            ///获取post请求参数
            paramsStr = this.getPostParm(request);
        } else if ("GET".equals(method)) {
            //Get方式请求参数
            paramsStr = request.getQueryString();
        }
        ///日志模板
        String Template = "请求方式：%s ；请求地址：%s ；请求参数：%s 。";
        ///记录日志
        log.info(String.format(Template, method, url, paramsStr));
    }

    private String getPostParm(HttpServletRequest request) {
        try {
            BodyReaderWrapper bodyReaderWrapper = new BodyReaderWrapper(request);
            return getPostBodyParams(bodyReaderWrapper.getInputStream(), request.getCharacterEncoding());
        } catch (Exception e) {
            ///记录日志
            log.error("getPostParm exception", e);
            return "getPostParm exception";
        }
    }

    /**
     * 获取POST请求中Body参数
     *
     * @param
     * @return 字符串
     */
    private String getPostBodyParams(ServletInputStream inputStream, String charset) throws Exception {
        try {

            String body = StreamUtils.copyToString(inputStream, Charset.forName(charset));

            if (StringUtils.isEmpty(body)) {
                return "";
            }

            return body;

        } catch (Exception e) {
            throw e;
        }
    }

}
