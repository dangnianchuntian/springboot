/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：LombokRequest.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.controller.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("Lombok演示请求实体")
@Data
public class LombokRequest {

    @ApiModelProperty(value = "整数类型")
    private Integer intLombok;
    @ApiModelProperty(value = "字符串类型")
    private String strLombok;
    @ApiModelProperty(value = "布尔类型")
    private Boolean boleanLombok;
    @ApiModelProperty(value = "实体类型")
    private Person personLombok;
    @ApiModelProperty(value = "BigDecimal类型")
    private BigDecimal bigDecimal;
    @Data
    private static class Person {
        private Integer age;
        private String name;
    }


}
