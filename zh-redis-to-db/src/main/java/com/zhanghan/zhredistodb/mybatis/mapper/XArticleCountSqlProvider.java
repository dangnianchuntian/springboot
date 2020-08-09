/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战解决高并发数据入库: Redis 缓存+MySQL 批量入库
 * 类名称：XArticleCountSqlProvider.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhredistodb.mybatis.mapper;


import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.zhanghan.zhredistodb.dto.ArticleCountDto;

public class XArticleCountSqlProvider {

    private static final MessageFormat mf = new MessageFormat("(#'{'list[{0}].buNo},#'{'list[{0}].customerId},#'{'list[{0}].articleNo},#'{'list[{0}].readTime})");
    public String batchAdd(Map map) {
        List<ArticleCountDto> record_list = (List<ArticleCountDto>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO zh_article_count ");
        sb.append("(bu_no, customer_id, article_no, read_time) ");
        sb.append("VALUES ");
        for (int i = 0; i < record_list.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < record_list.size() - 1) {
                sb.append(",");
            }
        }
        String sql = sb.toString().trim();
        return sql;
    }
}