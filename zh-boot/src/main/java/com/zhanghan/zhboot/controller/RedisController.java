package com.zhanghan.zhboot.controller;

import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;
    @Autowired
    private RedisTemplate<String, Long> longRedisTemplate;
    @Autowired
    private RedisTemplate<String, Boolean> booleanRedisTemplate;

    @RequestMapping(value = "/get/redis", method = RequestMethod.GET)
    public Map xmlAnalysis() {
        String strRedisKey = "zh:boot:String";
        String longRedisKey = "zh:boot:long";
        String booleanRedisKey = "zh:boot:bollean";

        String strRedisValue = strRedisTemplate.opsForValue().get(strRedisKey);
        if (StringUtils.isNullOrEmpty(strRedisValue)) {
            strRedisTemplate.opsForValue().set(strRedisKey, "张晗");
        }

        Long longRedisValue = longRedisTemplate.opsForValue().get(longRedisKey);
        if (ObjectUtils.isEmpty(longRedisValue)) {
            longRedisTemplate.opsForValue().set(longRedisKey, 1L);
        }

        Boolean booleanRedisValue = booleanRedisTemplate.opsForValue().get(booleanRedisKey);
        if (ObjectUtils.isEmpty(booleanRedisValue)) {
            booleanRedisTemplate.opsForValue().set(booleanRedisKey, true);
        }

        strRedisValue = strRedisTemplate.opsForValue().get(strRedisKey);
        longRedisValue = longRedisTemplate.opsForValue().get(longRedisKey);
        booleanRedisValue = booleanRedisTemplate.opsForValue().get(booleanRedisKey);

        Map result = new HashMap();
        result.put(strRedisKey, strRedisValue);
        result.put(longRedisKey, longRedisValue);
        result.put(booleanRedisKey, booleanRedisValue);

        return result;
    }

}
