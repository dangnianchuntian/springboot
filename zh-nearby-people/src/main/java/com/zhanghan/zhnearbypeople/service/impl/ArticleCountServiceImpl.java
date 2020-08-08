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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zhanghan.zhnearbypeople.controller.request.PostArticleViewsRequest;
import com.zhanghan.zhnearbypeople.dto.ArticleCountDto;
import com.zhanghan.zhnearbypeople.service.ArticleCountService;
import com.zhanghan.zhnearbypeople.util.wrapper.WrapMapper;

@Service
public class ArticleCountServiceImpl implements ArticleCountService {

    private static Logger logger = LoggerFactory.getLogger(ArticleCountServiceImpl.class);

    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;

    @Value("${zh.article.count.redis.key:zh}")
    private String zhArticleCountRedisKey;

    @Value("#{T(java.lang.Integer).parseInt('${zh..article.read.num:3}')}")
    private Integer articleReadNum;

    /**
     * 记录用户访问记录
     */
    @Override
    public Object postArticleViews(PostArticleViewsRequest postArticleViewsRequest) {

        return WrapMapper.ok();
    }

    /**
     * 批量将缓存中的数据同步到MySQL
     */
    @Override
    public Object postBatchRedisToDb() {

        Date now = new Date();

        while (true) {

            List<String> strArticleCountList =
                    strRedisTemplate.opsForList().range(zhArticleCountRedisKey, 0, articleReadNum);

            if (CollectionUtils.isEmpty(strArticleCountList)) {
                return WrapMapper.ok();
            }

            List<ArticleCountDto> articleCountDtoList = new ArrayList<>();



            //过滤出本次定时任务之前的缓存中数据,防止死循环
            List<ArticleCountDto> beforeArticleCountDtoList = articleCountDtoList.stream().filter(x -> x.getReadTime()
                    .before(now)).collect(Collectors.toList());

            if (CollectionUtils.isEmpty(beforeArticleCountDtoList)) {
                return WrapMapper.ok();
            }
            Integer delSize = beforeArticleCountDtoList.size();

            strRedisTemplate.opsForList().trim(zhArticleCountRedisKey, delSize, -1L);
        }

    }

}
