package com.zhanghan.zhsignin.mybatis.entity;

import lombok.Data;

import java.util.Date;

@Data
public class XZhSignInHistEntity {
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