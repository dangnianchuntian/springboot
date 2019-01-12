package com.zhanghan.zhboot.controller;

import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;

    @RequestMapping(value = "/get/redis", method = RequestMethod.GET)
    public String xmlAnalysis() {
        String strRedisKey = "zh:boot:string";
        String strRedisValue = strRedisTemplate.opsForValue().get(strRedisKey);
        if (StringUtils.isNullOrEmpty(strRedisValue)) {
            strRedisTemplate.opsForValue().set(strRedisKey, "张晗");
        }
        strRedisValue = strRedisTemplate.opsForValue().get(strRedisKey);

        return strRedisValue;
    }

}
