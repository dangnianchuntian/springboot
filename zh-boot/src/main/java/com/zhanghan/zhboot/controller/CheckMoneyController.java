/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：CheckMoneyController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.controller;

import com.zhanghan.zhboot.controller.request.CheckMoneyRequest;
import com.zhanghan.zhboot.util.MoneyUtil;
import com.zhanghan.zhboot.util.wrapper.WrapMapper;
import com.zhanghan.zhboot.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "演示校验金额控制器", tags = {"演示校验金额控制器"})
public class CheckMoneyController {

    @ApiOperation(value = "演示金额校验", tags = {"演示校验金额控制器"})
    @RequestMapping(value = "/check/money", method = RequestMethod.POST)
    public Wrapper lombok(@RequestBody CheckMoneyRequest checkMoneyRequest) {

        BigDecimal money = checkMoneyRequest.getMoney();

        //校验金额是否符合要求
        boolean legal = MoneyUtil.judgeTwoDecimal(money);

        Map<String, Object> map = new HashMap();
        map.put("money", money);
        map.put("islegal",legal);

        return WrapMapper.ok(map);
    }



}
