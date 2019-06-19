/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：MobileCheckRequest.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://blog.csdn.net/zhanghan18333611647
 */

package com.zhanghan.zhboot.controller.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

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
