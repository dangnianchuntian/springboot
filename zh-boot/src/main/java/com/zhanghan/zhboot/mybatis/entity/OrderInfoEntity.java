package com.zhanghan.zhboot.mybatis.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OrderInfoEntity {

    private Long id;

    private String no;

    private String userNo;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Integer param1;

    private Integer param2;

    private Integer param3;

    private String param4;

    private String param5;

    private String param6;
}