/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战解决高并发数据入库: Redis 缓存+MySQL 批量入库
 * 类名称：ArticleCountController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhredistodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhanghan.zhredistodb.controller.request.PostArticleViewsRequest;
import com.zhanghan.zhredistodb.service.ArticleCountService;

@RestController
public class ArticleCountController {

    @Autowired
    private ArticleCountService articleCountService;

   /**
    * 记录用户访问记录
    */
    @RequestMapping(value = "/post/article/views", method = RequestMethod.POST)
    public Object postArticleViews(@RequestBody @Validated PostArticleViewsRequest postArticleViewsRequest) {
        return articleCountService.postArticleViews(postArticleViewsRequest);
    }
    /**
     *  批量将缓存中的数据同步到MySQL（模拟定时任务操作）
     */

    @RequestMapping(value = "/post/batch", method = RequestMethod.POST)
    public Object postBatch() {
        return articleCountService.postBatchRedisToDb();
    }

}
