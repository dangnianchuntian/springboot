/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：MobilePreFixProperties.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://blog.csdn.net/zhanghan18333611647
 */

package com.zhanghan.zhboot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "mobile")
@Data
public class MobilePreFixProperties {

    // 查询国家所有主体 url
    private Map<String, String> prefixs = new HashMap<>();

}
