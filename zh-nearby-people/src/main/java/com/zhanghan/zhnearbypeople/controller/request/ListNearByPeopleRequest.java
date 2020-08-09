/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战分页查询附近的人: Redis+GeoHash+Lua
 * 类名称：ListNearByPeopleRequest.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhnearbypeople.controller.request;

import com.sun.istack.internal.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListNearByPeopleRequest {

    @NotNull
    private String customerId;

    @NotNull
    private Integer pageIndex;

    @NotNull
    private Integer pageSize;

}
