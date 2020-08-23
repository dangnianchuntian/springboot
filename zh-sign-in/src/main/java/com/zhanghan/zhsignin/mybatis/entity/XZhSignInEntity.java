/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战:签到奖励实现方案
 * 类名称：XZhSignInEntity.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhsignin.mybatis.entity;

import lombok.Data;

import java.util.Date;

@Data
public class XZhSignInEntity {
    private Long id;

    private String buNo;

    private String customerId;

    private Date signInDate;

    private Integer rewardMoney;

    private Integer continuiteDay;

    private Date createTime;

    private Date updateTime;

    private Integer param1;

    private Integer param2;

    private Integer param3;

    private String param4;

    private String param5;

    private String param6;

}