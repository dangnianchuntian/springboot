/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战:签到奖励实现方案
 * 类名称：XZhSignInSqlProvider.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhsignin.mybatis.mapper;

import com.zhanghan.zhsignin.mybatis.entity.XZhSignInEntity;
import org.apache.ibatis.jdbc.SQL;

public class XZhSignInSqlProvider {

    public String insertSelective(XZhSignInEntity record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("zh_sign_in");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getBuNo() != null) {
            sql.VALUES("bu_no", "#{buNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCustomerId() != null) {
            sql.VALUES("customer_id", "#{customerId,jdbcType=VARCHAR}");
        }
        
        if (record.getSignInDate() != null) {
            sql.VALUES("sign_in_date", "#{signInDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getRewardMoney() != null) {
            sql.VALUES("reward_money", "#{rewardMoney,jdbcType=INTEGER}");
        }
        
        if (record.getContinuiteDay() != null) {
            sql.VALUES("continuite_day", "#{continuiteDay,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getParam1() != null) {
            sql.VALUES("param1", "#{param1,jdbcType=INTEGER}");
        }
        
        if (record.getParam2() != null) {
            sql.VALUES("param2", "#{param2,jdbcType=INTEGER}");
        }
        
        if (record.getParam3() != null) {
            sql.VALUES("param3", "#{param3,jdbcType=INTEGER}");
        }
        
        if (record.getParam4() != null) {
            sql.VALUES("param4", "#{param4,jdbcType=VARCHAR}");
        }
        
        if (record.getParam5() != null) {
            sql.VALUES("param5", "#{param5,jdbcType=VARCHAR}");
        }
        
        if (record.getParam6() != null) {
            sql.VALUES("param6", "#{param6,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(XZhSignInEntity record) {
        SQL sql = new SQL();
        sql.UPDATE("zh_sign_in");

        if (record.getBuNo() != null) {
            sql.SET("bu_no = #{buNo,jdbcType=VARCHAR}");
        }

        if (record.getCustomerId() != null) {
            sql.SET("customer_id = #{customerId,jdbcType=VARCHAR}");
        }

        if (record.getSignInDate() != null) {
            sql.SET("sign_in_date = #{signInDate,jdbcType=TIMESTAMP}");
        }

        if (record.getRewardMoney() != null) {
            sql.SET("reward_money = #{rewardMoney,jdbcType=INTEGER}");
        }

        if (record.getContinuiteDay() != null) {
            sql.SET("continuite_day = #{continuiteDay,jdbcType=INTEGER}");
        }

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }

        if (record.getParam1() != null) {
            sql.SET("param1 = #{param1,jdbcType=INTEGER}");
        }

        if (record.getParam2() != null) {
            sql.SET("param2 = #{param2,jdbcType=INTEGER}");
        }

        if (record.getParam3() != null) {
            sql.SET("param3 = #{param3,jdbcType=INTEGER}");
        }

        if (record.getParam4() != null) {
            sql.SET("param4 = #{param4,jdbcType=VARCHAR}");
        }

        if (record.getParam5() != null) {
            sql.SET("param5 = #{param5,jdbcType=VARCHAR}");
        }

        if (record.getParam6() != null) {
            sql.SET("param6 = #{param6,jdbcType=VARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }

}