/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：SpringBoot项目接入ELK
 * 类名称：LombokRequest.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhelkboot.controller.request;


import lombok.Data;

@Data
public class LombokRequest {

    private Integer intLombok;
    private String strLombok;
    private Boolean boleanLombok;
    private Person personLombok;

    @Data
    private static class Person {
        private Integer age;
        private String name;
    }


}
