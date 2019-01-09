package com.zhanghan.zhboot.service.impl;

import com.zhanghan.zhboot.mybatis.mapper.order.OrderInfoMapper;
import com.zhanghan.zhboot.mybatis.mapper.user.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderImpl {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
}
