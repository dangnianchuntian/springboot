/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战:Redis批量操作轻松实现百倍性能提升
 * 类名称：BatchRedisServiceImpl.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhredisbatch.service.impl;

import com.zhanghan.zhredisbatch.controller.request.ListMultiGetRequest;
import com.zhanghan.zhredisbatch.controller.request.PostMultiDeleteRequest;
import com.zhanghan.zhredisbatch.controller.request.PostMultiSetRequest;
import com.zhanghan.zhredisbatch.controller.response.ListMultiGetResponse;
import com.zhanghan.zhredisbatch.dto.BatchRedisDto;
import com.zhanghan.zhredisbatch.service.BatchRedisService;
import com.zhanghan.zhredisbatch.util.wrapper.WrapMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class BatchRedisServiceImpl implements BatchRedisService {

    private static Logger logger = LoggerFactory.getLogger(BatchRedisServiceImpl.class);

    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;

    /**
     * Redis批量Set
     */
    @Override
    public Object postMultiSet(PostMultiSetRequest postMultiSetRequest) {
        Map<String, String> batchSetMap = new HashMap<>();
        List<BatchRedisDto> batchRedisDtoList = postMultiSetRequest.getBatchRedisDtoList();
        for (BatchRedisDto batchRedisDto : batchRedisDtoList) {
            batchSetMap.put(batchRedisDto.getRedisKey(), batchRedisDto.getRedisValue());
        }

        strRedisTemplate.opsForValue().multiSet(batchSetMap);
        return WrapMapper.ok();
    }

    /**
     * Redis批量Get
     */
    @Override
    public Object listMultiGet(ListMultiGetRequest listMultiGetRequest) {
        List<String> keyList = listMultiGetRequest.getKeyList();
        List<String> valueList = strRedisTemplate.opsForValue().multiGet(keyList);
        List<BatchRedisDto> batchRedisDtoList = new ArrayList<>();
        ListMultiGetResponse listMultiGetResponse = new ListMultiGetResponse();

        for (int i = 0; i < valueList.size(); i++) {
            String value = valueList.get(i);
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            BatchRedisDto batchRedisDto = new BatchRedisDto();
            batchRedisDto.setRedisKey(keyList.get(i));
            batchRedisDto.setRedisValue(value);
            batchRedisDtoList.add(batchRedisDto);
        }

        listMultiGetResponse.setBatchRedisDtoList(batchRedisDtoList);

        return WrapMapper.ok(listMultiGetResponse);
    }

    /**
     * Redis批量Set且设置失效时间
     */
    @Override
    public Object postMultiSetAndExpire(PostMultiSetRequest postMultiSetRequest) {
        List<BatchRedisDto> batchRedisDtoList = postMultiSetRequest.getBatchRedisDtoList();
        strRedisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                for (BatchRedisDto batchRedisDto : batchRedisDtoList) {
                    String key = batchRedisDto.getRedisKey();
                    String value = batchRedisDto.getRedisValue();
                    connection.set(key.getBytes(), value.getBytes(), Expiration.from(1, TimeUnit.DAYS), RedisStringCommands.SetOption.UPSERT);
                }
                return null;
            }
        });

        return WrapMapper.ok();
    }

    /**
     * Redis批量Delete
     */
    @Override
    public Object postMultiDelete(PostMultiDeleteRequest postMultiDeleteRequest) {
        strRedisTemplate.delete(postMultiDeleteRequest.getKeyList());
        return WrapMapper.ok();
    }

}
