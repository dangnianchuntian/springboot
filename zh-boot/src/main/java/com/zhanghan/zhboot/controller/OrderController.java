/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：OrderController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.controller;

import com.zhanghan.zhboot.controller.request.OrderRequest;
import com.zhanghan.zhboot.service.OrderService;
import com.zhanghan.zhboot.util.wrapper.WrapMapper;
import com.zhanghan.zhboot.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "演示多数据源控制器", tags = {"演示多数据源控制器"})
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "多数据源获取订单方式", tags = {"演示多数据源控制器"})
    @RequestMapping(value = "/get/order/borrow", method = RequestMethod.POST)
    public Wrapper getOrderBorrow(@RequestBody OrderRequest orderRequest) {
        return WrapMapper.ok(orderService.findOrder(orderRequest));
    }
}
