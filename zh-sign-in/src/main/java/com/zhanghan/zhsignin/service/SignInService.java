/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战:签到奖励实现方案
 * 类名称：SignInService.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhsignin.service;


import com.zhanghan.zhsignin.controller.request.ListSignInDetailRequest;
import com.zhanghan.zhsignin.controller.request.PostSignInRequest;

public interface SignInService {


    Object listSignInDetail(ListSignInDetailRequest listSignInDetailRequest);

    Object postSignIn(PostSignInRequest postSignInRequest);

}
