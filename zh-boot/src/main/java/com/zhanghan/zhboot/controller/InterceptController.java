/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：InterceptController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.controller;

import com.zhanghan.zhboot.controller.request.InterceptRequest;
import com.zhanghan.zhboot.util.wrapper.WrapMapper;
import com.zhanghan.zhboot.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "演示拦截控制器", tags = {"演示拦截控制器"})
public class InterceptController {

    @ApiOperation(value = "演示拦截", tags = {"演示拦截控制器"})
    @RequestMapping(value = "/intercept", method = RequestMethod.POST)
    public Wrapper intercept(@Valid @RequestBody(required =false) InterceptRequest interceptRequest) {
        System.out.println(interceptRequest.toString());
        Map<String, Object> map = new HashMap();
        map.put("intLombok", interceptRequest.getIntLombok());
        map.put("strLombok", interceptRequest.getStrLombok());
        return WrapMapper.ok(map);
    }

}
