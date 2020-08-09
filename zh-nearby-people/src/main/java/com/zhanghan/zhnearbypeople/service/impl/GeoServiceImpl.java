/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战解决高并发数据入库: Redis 缓存+MySQL 批量入库
 * 类名称：RedisConfig.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhnearbypeople.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.zhanghan.zhnearbypeople.controller.request.ListNearByPeopleRequest;
import com.zhanghan.zhnearbypeople.controller.request.PostGeoRequest;
import com.zhanghan.zhnearbypeople.dto.NearByPeopleDto;
import com.zhanghan.zhnearbypeople.service.GeoService;
import com.zhanghan.zhnearbypeople.util.RedisLuaUtil;
import com.zhanghan.zhnearbypeople.util.wrapper.WrapMapper;

@Service
public class GeoServiceImpl implements GeoService {

    private static Logger logger = LoggerFactory.getLogger(GeoServiceImpl.class);

    @Autowired
    private RedisTemplate<String, Object> objRedisTemplate;

    @Value("${zh.geo.redis.key:zh:geo}")
    private String zhGeoRedisKey;

    @Value("${zh.geo.zset.redis.key:zh:geo:zset:}")
    private String zhGeoZsetRedisKey;

    /**
     * 记录用户访问记录
     */
    @Override
    public Object postGeo(PostGeoRequest postGeoRequest) {

        Long flag = objRedisTemplate.opsForGeo().add(zhGeoRedisKey, new RedisGeoCommands.GeoLocation<>(postGeoRequest
                .getCustomerId(), new Point(postGeoRequest.getLongitude(), postGeoRequest.getLatitude())));

        if (null != flag && flag > 0) {
            return WrapMapper.ok();
        }

        return WrapMapper.error();
    }

    /**
     * 分页查询附近的人
     */
    @Override
    public Object listNearByPeople(ListNearByPeopleRequest listNearByPeopleRequest) {

        String customerId = listNearByPeopleRequest.getCustomerId();

        String strZsetUserKey = zhGeoZsetRedisKey + customerId;

        List<NearByPeopleDto> nearByPeopleDtoList = new ArrayList<>();

        //如果是从第1页开始查,则将附近的人写入zset集合，以后页直接从zset中查
        if (1 == listNearByPeopleRequest.getPageIndex()) {
            List<String> scriptParams = new ArrayList<>();
            scriptParams.add(zhGeoRedisKey);
            scriptParams.add(customerId);
            scriptParams.add("10");
            scriptParams.add(RedisGeoCommands.DistanceUnit.KILOMETERS.getAbbreviation());
            scriptParams.add("asc");
            scriptParams.add("storedist");
            scriptParams.add(strZsetUserKey);

            Long executeResult = objRedisTemplate.execute(RedisLuaUtil.GEO_RADIUS_STOREDIST_SCRIPT(), scriptParams);

            if (null == executeResult || executeResult < 1) {
                return WrapMapper.ok(nearByPeopleDtoList);
            }

            //zset集合中去除自己
            Long remove = objRedisTemplate.opsForZSet().remove(strZsetUserKey, 0, 0);

        }

        nearByPeopleDtoList = listNearByPeopleFromZset(strZsetUserKey, listNearByPeopleRequest);

        return WrapMapper.ok(nearByPeopleDtoList);

    }

    private List<NearByPeopleDto> listNearByPeopleFromZset(String strZsetUserKey, ListNearByPeopleRequest
            listNearByPeopleRequest) {
        List<NearByPeopleDto> nearByPeopleDtoList = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> zsetUsers = objRedisTemplate.opsForZSet()
                .rangeWithScores(strZsetUserKey, listNearByPeopleRequest.getPageIndex(),
                        listNearByPeopleRequest.getPageSize());

        for (ZSetOperations.TypedTuple<Object> zsetUser : zsetUsers) {
            NearByPeopleDto nearByPeopleDto = new NearByPeopleDto();
            nearByPeopleDto.setCustomerId(zsetUser.getValue().toString());
            nearByPeopleDto.setDistance(zsetUser.getScore());
            nearByPeopleDtoList.add(nearByPeopleDto);
        }

        return nearByPeopleDtoList;
    }

}
