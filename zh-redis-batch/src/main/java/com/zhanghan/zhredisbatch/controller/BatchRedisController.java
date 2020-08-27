/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战:Redis批量操作轻松实现百倍性能提升
 * 类名称：BatchRedisController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhredisbatch.controller;

import com.zhanghan.zhredisbatch.controller.request.ListMultiGetRequest;
import com.zhanghan.zhredisbatch.controller.request.PostMultiDeleteRequest;
import com.zhanghan.zhredisbatch.controller.request.PostMultiSetRequest;
import com.zhanghan.zhredisbatch.service.BatchRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchRedisController {

    @Autowired
    private BatchRedisService batchRedisService;

    /**
     * Redis批量Set
     */
    @RequestMapping(value = "/post/multi/set", method = RequestMethod.POST)
    public Object postMultiSet(@RequestBody @Validated PostMultiSetRequest postMultiSetRequest) {
        return batchRedisService.postMultiSet(postMultiSetRequest);
    }

    /**
     * Redis批量Get
     */
    @RequestMapping(value = "/list/multi/get", method = RequestMethod.POST)
    public Object listMultiGet(@RequestBody @Validated ListMultiGetRequest listMultiGetRequest) {
        return batchRedisService.listMultiGet(listMultiGetRequest);
    }

    /**
     * Redis批量Set且设置失效时间
     */
    @RequestMapping(value = "/post/multi/set/expire", method = RequestMethod.POST)
    public Object postMultiSetAndExpire(@RequestBody @Validated PostMultiSetRequest postMultiSetRequest) {
        return batchRedisService.postMultiSetAndExpire(postMultiSetRequest);
    }

    /**
     * Redis批量Delete
     */
    @RequestMapping(value = "/post/multi/delete", method = RequestMethod.POST)
    public Object postMultiDelete(@RequestBody @Validated PostMultiDeleteRequest postMultiDeleteRequest) {
        return batchRedisService.postMultiDelete(postMultiDeleteRequest);
    }

}
