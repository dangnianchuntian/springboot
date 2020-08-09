/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战解决高并发数据入库: Redis 缓存+MySQL 批量入库
 * 类名称：ArticleCountServiceImpl.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhredistodb.service.impl;

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

import com.alibaba.fastjson.JSON;
import com.zhanghan.zhredistodb.controller.request.PostArticleViewsRequest;
import com.zhanghan.zhredistodb.dto.ArticleCountDto;
import com.zhanghan.zhredistodb.mybatis.mapper.XArticleCountMapper;
import com.zhanghan.zhredistodb.service.ArticleCountService;
import com.zhanghan.zhredistodb.util.wrapper.WrapMapper;

import cn.hutool.core.util.IdUtil;

@Service
public class ArticleCountServiceImpl implements ArticleCountService {

    private static Logger logger = LoggerFactory.getLogger(ArticleCountServiceImpl.class);

    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;

    @Autowired
    private XArticleCountMapper xArticleCountMapper;

    @Value("${zh.article.count.redis.key:zh}")
    private String zhArticleCountRedisKey;

    @Value("#{T(java.lang.Integer).parseInt('${zh.article.read.num:3}')}")
    private Integer articleReadNum;

    /**
     * 记录用户访问记录
     */
    @Override
    public Object postArticleViews(PostArticleViewsRequest postArticleViewsRequest) {

        ArticleCountDto articleCountDto = new ArticleCountDto();
        articleCountDto.setBuNo(IdUtil.simpleUUID());
        articleCountDto.setCustomerId(postArticleViewsRequest.getCustomerId());
        articleCountDto.setArticleNo(postArticleViewsRequest.getArticleNo());
        articleCountDto.setReadTime(new Date());
        String strArticleCountDto = JSON.toJSONString(articleCountDto);
        strRedisTemplate.opsForList().rightPush(zhArticleCountRedisKey, strArticleCountDto);
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

            strArticleCountList.stream().forEach(x -> {
                ArticleCountDto articleCountDto = JSON.parseObject(x, ArticleCountDto.class);
                articleCountDtoList.add(articleCountDto);
            });

            //过滤出本次定时任务之前的缓存中数据,防止死循环
            List<ArticleCountDto> beforeArticleCountDtoList = articleCountDtoList.stream().filter(x -> x.getReadTime()
                    .before(now)).collect(Collectors.toList());

            if (CollectionUtils.isEmpty(beforeArticleCountDtoList)) {
                return WrapMapper.ok();
            }

            xArticleCountMapper.batchAdd(beforeArticleCountDtoList);

            Integer delSize = beforeArticleCountDtoList.size();

            strRedisTemplate.opsForList().trim(zhArticleCountRedisKey, delSize, -1L);
        }

    }

}
