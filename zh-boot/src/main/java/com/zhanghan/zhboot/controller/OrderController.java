package com.zhanghan.zhboot.controller;

import com.zhanghan.zhboot.controller.request.OrderRequest;
import com.zhanghan.zhboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/get/order/borrow", method = RequestMethod.POST)
    public Object getOrderBorrow(@RequestBody OrderRequest orderRequest) {
        return orderService.findOrder(orderRequest);
    }
}
