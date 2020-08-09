/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战解决高并发数据入库: Redis 缓存+MySQL 批量入库
 * 类名称：XArticleCountMapper.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhredistodb.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zhanghan.zhredistodb.dto.ArticleCountDto;

@Mapper
public interface XArticleCountMapper {

    @InsertProvider(type = XArticleCountSqlProvider.class, method = "batchAdd")
    int batchAdd(@Param("list") List<ArticleCountDto> record);
}