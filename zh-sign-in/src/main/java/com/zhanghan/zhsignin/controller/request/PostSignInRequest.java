/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战:签到奖励实现方案
 * 类名称：PostSignInRequest.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhsignin.controller.request;


import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class PostSignInRequest {

    @NotNull
    private String customerId;

    public PostSignInRequest() {
    }
}
