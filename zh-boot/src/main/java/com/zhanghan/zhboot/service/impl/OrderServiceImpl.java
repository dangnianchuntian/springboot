/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：OrderServiceImpl.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.service.impl;

import com.zhanghan.zhboot.controller.request.OrderRequest;
import com.zhanghan.zhboot.mybatis.entity.OrderInfoEntity;
import com.zhanghan.zhboot.mybatis.entity.UserInfoEntity;
import com.zhanghan.zhboot.mybatis.mapper.order.OrderInfoMapper;
import com.zhanghan.zhboot.mybatis.mapper.user.UserInfoMapper;
import com.zhanghan.zhboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public Object findOrder(OrderRequest orderRequest) {
        UserInfoEntity userInfoEntity = userInfoMapper.findByMobile(orderRequest.getMobile());
        if (ObjectUtils.isEmpty(userInfoEntity)) {
            return null;
        }

        OrderInfoEntity orderInfoEntity = orderInfoMapper.findByUserNo(userInfoEntity.getNo());
        return orderInfoEntity;
    }
}
