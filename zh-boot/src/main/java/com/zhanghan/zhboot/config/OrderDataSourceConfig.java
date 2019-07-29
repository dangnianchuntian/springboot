/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：OrderDataSourceConfig.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zhanghan.zhboot.properties.OrderDataSourceProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = OrderDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "orderSqlSessionFactory")
public class OrderDataSourceConfig {

    static final String PACKAGE = "com.zhanghan.zhboot.mybatis.mapper.order";

    @Autowired
    private OrderDataSourceProperties orderDataSourceProperties;


    @Bean(name = "orderDataSource")
    public DataSource orderDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(orderDataSourceProperties.getUrl());
        config.setUsername(orderDataSourceProperties.getUsername());
        config.setPassword(orderDataSourceProperties.getPassword());
        config.setConnectionTestQuery("select 1");
        config.setConnectionTimeout(orderDataSourceProperties.getConnectionTimeout());
        config.setIdleTimeout(orderDataSourceProperties.getIdleTimeout());
        config.setMaxLifetime(orderDataSourceProperties.getMaxLifetime());
        config.setMaximumPoolSize(orderDataSourceProperties.getMaximumPoolSize());
        config.setMinimumIdle(orderDataSourceProperties.getMinimumIdle());
        return new HikariDataSource(config);
    }

    @Bean(name = "orderTransactionManager")
    public DataSourceTransactionManager orderTransactionManager() {
        return new DataSourceTransactionManager(orderDataSource());
    }

    @Bean(name = "orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("orderDataSource") DataSource riskDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(riskDataSource);
        return sessionFactory.getObject();
    }
}
