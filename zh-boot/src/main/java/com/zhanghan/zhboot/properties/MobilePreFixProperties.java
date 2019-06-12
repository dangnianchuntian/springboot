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
