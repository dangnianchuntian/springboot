/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：WebMvcConfig.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhelkboot.config;

import com.zhanghan.zhelkboot.intercept.InterceptLog;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptLog()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
