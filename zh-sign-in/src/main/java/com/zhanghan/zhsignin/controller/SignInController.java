/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战:签到奖励实现方案
 * 类名称：SignInController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhsignin.controller;

import com.zhanghan.zhsignin.controller.request.PostSignInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhanghan.zhsignin.controller.request.ListSignInDetailRequest;
import com.zhanghan.zhsignin.service.SignInService;

@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;

    /**
     * 查询签到记录
     */
    @RequestMapping(value = "/list/sign/in/detail", method = RequestMethod.POST)
    public Object listSignInDetail(@RequestBody @Validated ListSignInDetailRequest listSignInDetailRequest) {
        return signInService.listSignInDetail(listSignInDetailRequest);
    }

    /**
     * 用户进行签到
     */
    @RequestMapping(value = "/post/sign/in", method = RequestMethod.POST)
    public Object postSignIn(@RequestBody @Validated PostSignInRequest postSignInRequest) {
        return signInService.postSignIn(postSignInRequest);
    }

}
