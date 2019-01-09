package com.zhanghan.zhboot.mybatis.mapper.order;

import com.zhanghan.zhboot.mybatis.entity.OrderInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;


@Mapper
public interface OrderInfoMapper {

    @Select({
            "select",
            "id, no, user_no, remark, create_time, update_time, param1, param2, param3, param4, ",
            "param5, param6",
            "from order_info",
            "where user_no = #{userNo,jdbcType=VARCHAR}",
            "limit 1"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="no", property="no", jdbcType=JdbcType.VARCHAR),
            @Result(column="user_no", property="userNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="param1", property="param1", jdbcType=JdbcType.INTEGER),
            @Result(column="param2", property="param2", jdbcType=JdbcType.INTEGER),
            @Result(column="param3", property="param3", jdbcType=JdbcType.INTEGER),
            @Result(column="param4", property="param4", jdbcType=JdbcType.VARCHAR),
            @Result(column="param5", property="param5", jdbcType=JdbcType.VARCHAR),
            @Result(column="param6", property="param6", jdbcType=JdbcType.VARCHAR)
    })
    OrderInfoEntity findByUserNo(String userNo);
}
