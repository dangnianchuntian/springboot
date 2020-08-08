/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：MobileCheckRequest.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.controller.request;

import com.sun.istack.internal.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("手机号校验请求实体")
@Data
public class MobileCheckRequest {

    @ApiModelProperty(value = "国家编码",required = true)
    @NotNull
    private String countryCode;

    @ApiModelProperty(value = "手机号",required = true)
    @NotNull
    private String mobile;


}
