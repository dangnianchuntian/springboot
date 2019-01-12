package com.zhanghan.zhboot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private int database;

    private String host;

    private int port;

    private String password;

    private Integer timeout;


}
