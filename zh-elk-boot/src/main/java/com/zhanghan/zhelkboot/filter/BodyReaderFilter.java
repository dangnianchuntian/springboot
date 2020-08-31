/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：BodyReaderFilter.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhelkboot.filter;

import com.zhanghan.zhelkboot.intercept.BodyReaderWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName="bodyReaderFilter",urlPatterns="/*")
public class BodyReaderFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ServletRequest requestWrapper=null;
        if(request instanceof HttpServletRequest) {
            requestWrapper=new BodyReaderWrapper((HttpServletRequest)request);
        }
        if(requestWrapper==null) {
            chain.doFilter(request, response);
        }else {
            chain.doFilter(requestWrapper, response);
        }

    }

    @Override
    public void destroy() {
        // do nothing

    }
}
