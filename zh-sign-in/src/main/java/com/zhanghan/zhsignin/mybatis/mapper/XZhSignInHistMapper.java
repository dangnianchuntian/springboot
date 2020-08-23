/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战:签到奖励实现方案
 * 类名称：XZhSignInHistMapper.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhsignin.mybatis.mapper;

import com.zhanghan.zhsignin.mybatis.entity.XZhSignInHistEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface XZhSignInHistMapper {

    @Select({
        "select",
        "id, bu_no, customer_id, sign_in_date, reward_money, continuite_day, create_time, ",
        "update_time, param1, param2, param3, param4, param5, param6",
        "from zh_sign_in_hist",
        "where customer_id = #{customerId,jdbcType=VARCHAR} order by id desc limit #{limit,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="bu_no", property="buNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.VARCHAR),
        @Result(column="sign_in_date", property="signInDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="reward_money", property="rewardMoney", jdbcType=JdbcType.INTEGER),
        @Result(column="continuite_day", property="continuiteDay", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="param1", property="param1", jdbcType=JdbcType.INTEGER),
        @Result(column="param2", property="param2", jdbcType=JdbcType.INTEGER),
        @Result(column="param3", property="param3", jdbcType=JdbcType.INTEGER),
        @Result(column="param4", property="param4", jdbcType=JdbcType.VARCHAR),
        @Result(column="param5", property="param5", jdbcType=JdbcType.VARCHAR),
        @Result(column="param6", property="param6", jdbcType=JdbcType.VARCHAR)
    })
    List<XZhSignInHistEntity> listByCustomerIdAndLimit(@Param("customerId") String customerId, @Param("limit") Integer limit);

    @InsertProvider(type=XZhSignInHistSqlProvider.class, method="insertSelective")
    int insertSelective(XZhSignInHistEntity record);

}