/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：RedisController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.controller;

import com.mysql.jdbc.StringUtils;
import com.zhanghan.zhboot.util.wrapper.WrapMapper;
import com.zhanghan.zhboot.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "演示Redis控制器", tags = {"演示Redis控制器"})
@RestController
public class RedisController {

    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;
    @Autowired
    private RedisTemplate<String, Long> longRedisTemplate;
    @Autowired
    private RedisTemplate<String, Boolean> booleanRedisTemplate;

    @ApiOperation(value = "获取Redis中的值", tags = {"演示Redis控制器"})
    @RequestMapping(value = "/get/redis", method = RequestMethod.GET)
    public Wrapper xmlAnalysis() {
        String strRedisKey = "zh:boot:String";
        String longRedisKey = "zh:boot:long";
        String booleanRedisKey = "zh:boot:bollean";

        String strRedisValue = strRedisTemplate.opsForValue().get(strRedisKey);
        if (StringUtils.isNullOrEmpty(strRedisValue)) {
            logger.info("get redis strRedisValue is empty;strRedisKey {}", strRedisKey);
            strRedisTemplate.opsForValue().set(strRedisKey, "张晗");
        }

        Long longRedisValue = longRedisTemplate.opsForValue().get(longRedisKey);
        if (ObjectUtils.isEmpty(longRedisValue)) {
            logger.info("get redis longRedisValue is empty;longRedisKey {}", longRedisKey);
            longRedisTemplate.opsForValue().set(longRedisKey, 1L);
        }

        Boolean booleanRedisValue = booleanRedisTemplate.opsForValue().get(booleanRedisKey);
        if (ObjectUtils.isEmpty(booleanRedisValue)) {
            logger.info("get redis booleanRedisValue is empty;booleanRedisKey {}", booleanRedisKey);
            booleanRedisTemplate.opsForValue().set(booleanRedisKey, true);
        }

        strRedisValue = strRedisTemplate.opsForValue().get(strRedisKey);
        longRedisValue = longRedisTemplate.opsForValue().get(longRedisKey);
        booleanRedisValue = booleanRedisTemplate.opsForValue().get(booleanRedisKey);

        Map result = new HashMap();
        result.put(strRedisKey, strRedisValue);
        result.put(longRedisKey, longRedisValue);
        result.put(booleanRedisKey, booleanRedisValue);

        return WrapMapper.ok(result);
    }

    @ApiOperation(value = "通道添加到Redis方式", tags = {"演示Redis控制器"})
    @RequestMapping(value = "/add/pipeline", method = RequestMethod.GET)
    public Wrapper addPipeline() {
        strRedisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                for (int i = 0; i < 100; i++) {
                    connection.set(("pipel:" + i).getBytes(), "123".getBytes());
                }
                return null;
            }
        });

        return WrapMapper.ok();
    }

    @ApiOperation(value = "普通多条添加到Redis方式", tags = {"演示Redis控制器"})
    @RequestMapping(value = "/add/single", method = RequestMethod.GET)
    public Wrapper addSingle() {
        for (int i = 0; i < 100; i++) {
            strRedisTemplate.opsForValue().set("single:" + i, "123");
        }

        return WrapMapper.ok();
    }

}
