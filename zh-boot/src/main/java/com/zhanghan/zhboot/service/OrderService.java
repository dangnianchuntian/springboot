package com.zhanghan.zhboot.service;

import com.zhanghan.zhboot.controller.request.OrderRequest;

public interface OrderService {


    Object findOrder(OrderRequest orderRequest);

}
