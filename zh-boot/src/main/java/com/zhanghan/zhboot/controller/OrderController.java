package com.zhanghan.zhboot.controller;

import com.zhanghan.zhboot.controller.request.OrderRequest;
import com.zhanghan.zhboot.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "演示多数据源控制器",tags = {"演示多数据源控制器"})
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value="多数据源获取订单方式",tags = {"演示多数据源控制器"})
    @RequestMapping(value = "/get/order/borrow", method = RequestMethod.POST)
    public Object getOrderBorrow(@RequestBody OrderRequest orderRequest) {
        return orderService.findOrder(orderRequest);
    }
}
