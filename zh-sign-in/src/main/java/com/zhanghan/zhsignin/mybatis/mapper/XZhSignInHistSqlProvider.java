package com.zhanghan.zhsignin.mybatis.mapper;

import com.zhanghan.zhsignin.mybatis.entity.XZhSignInHistEntity;
import org.apache.ibatis.jdbc.SQL;

public class XZhSignInHistSqlProvider {

    public String insertSelective(XZhSignInHistEntity record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("zh_sign_in_hist");
        
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
}