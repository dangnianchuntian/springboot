/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战解决高并发数据入库: Redis 缓存+MySQL 批量入库
 * 类名称：ArticleCountController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhredisbatch.controller;

import com.zhanghan.zhredisbatch.controller.request.ListMultiGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhanghan.zhredisbatch.controller.request.PostMultiSetRequest;
import com.zhanghan.zhredisbatch.service.BatchRedisService;

@RestController
public class BatchRedisController {

    @Autowired
    private BatchRedisService batchRedisService;

    /**
     * 记录用户访问记录
     */
    @RequestMapping(value = "/post/multi/set", method = RequestMethod.POST)
    public Object postMultiSet(@RequestBody @Validated PostMultiSetRequest postMultiSetRequest) {
        return batchRedisService.postMultiSet(postMultiSetRequest);
    }

    /**
     * 批量将缓存中的数据同步到MySQL（模拟定时任务操作）
     */
    @RequestMapping(value = "/list/multi/get", method = RequestMethod.POST)
    public Object listMultiGet(@RequestBody @Validated ListMultiGetRequest listMultiGetRequest) {
        return batchRedisService.listMultiGet(listMultiGetRequest);
    }

    @RequestMapping(value = "/post/multi/set/expire", method = RequestMethod.POST)
    public Object postMultiSetAndExpire(@RequestBody @Validated PostMultiSetRequest postMultiSetRequest) {
        return batchRedisService.postMultiSetAndExpire(postMultiSetRequest);
    }

}
