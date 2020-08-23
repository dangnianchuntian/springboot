/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战解决高并发数据入库: Redis 缓存+MySQL 批量入库
 * 类名称：ArticleCountServiceImpl.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhsignin.service.impl;

import cn.hutool.core.lang.UUID;
import com.zhanghan.zhsignin.config.SignInRewardMoneyListConfig;
import com.zhanghan.zhsignin.constant.SignInConstant;
import com.zhanghan.zhsignin.controller.request.ListSignInDetailRequest;
import com.zhanghan.zhsignin.controller.request.PostSignInRequest;
import com.zhanghan.zhsignin.controller.response.ListSignInDetailResponse;
import com.zhanghan.zhsignin.mybatis.entity.XZhSignInEntity;
import com.zhanghan.zhsignin.mybatis.entity.XZhSignInHistEntity;
import com.zhanghan.zhsignin.mybatis.mapper.XZhSignInHistMapper;
import com.zhanghan.zhsignin.mybatis.mapper.XZhSignInMapper;
import com.zhanghan.zhsignin.service.SignInService;
import com.zhanghan.zhsignin.util.DateUtils;
import com.zhanghan.zhsignin.util.wrapper.WrapMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.zhanghan.zhsignin.constant.SignInConstant.*;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private XZhSignInMapper xZhSignInMapper;

    @Autowired
    private XZhSignInHistMapper xZhSignInHistMapper;

    //校验连续天数是否为7
    @Value("#{T(java.lang.Integer).parseInt('${zh.sign.in.continuite.day.threshold:7}')}")
    public Integer continuiteDayThreshold;

    //签到奖励金币集合配置
    @Autowired
    public SignInRewardMoneyListConfig signInRewardMoneyListConfig;

    private static Long todayDateTime = DateUtils.getYesterdayDate().getTime();

    /**
     * 查询用户签到记录
     */
    @Override
    public Object listSignInDetail(ListSignInDetailRequest listSignInDetailRequest) {

        //若配置文件中未配置签到奖励则不展示签到记录
        List<Integer> signInRewardMoneyListConfigList = signInRewardMoneyListConfig.getList();
        if (CollectionUtils.isEmpty(signInRewardMoneyListConfigList)) {
            return WrapMapper.ok(new ListSignInDetailResponse(false));
        }

        String customerId = listSignInDetailRequest.getCustomerId();
        XZhSignInEntity xZhSignInEntity = xZhSignInMapper.findByCustomerId(customerId);

        List<ListSignInDetailResponse.SignInDetail> signInDetailList = signInRewardMoneyListConfigList.stream().map(aa -> new ListSignInDetailResponse.SignInDetail(0, aa)).collect(Collectors.toList());

        //该用户之前未签到过
        if (null == xZhSignInEntity) {
            return WrapMapper.ok(new ListSignInDetailResponse(TODAY_NOT_SIGN_IN, SignInConstant.CONTINUITE_DAY_ZERO, signInDetailList));
        }

        long signInDateTime = xZhSignInEntity.getSignInDate().getTime();
        long yesterdayDateTime = DateUtils.getYesterdayDate().getTime();

        //最近一次签到是否为昨日之前
        if (signInDateTime < yesterdayDateTime) {
            return WrapMapper.ok(new ListSignInDetailResponse(TODAY_NOT_SIGN_IN, SignInConstant.CONTINUITE_DAY_ZERO, signInDetailList));
        }

        //最近一次签到是否为昨日
        Integer todaySignStatus = TODAY_YES_SIGN_IN;
        Integer continuiteDay = xZhSignInEntity.getContinuiteDay();
        if (signInDateTime < todayDateTime) {
            //最近一次签到是昨日且之前已连续签到7日
            if (continuiteDay >= continuiteDayThreshold) {
                return WrapMapper.ok(new ListSignInDetailResponse(TODAY_NOT_SIGN_IN, SignInConstant.CONTINUITE_DAY_ZERO, signInDetailList));
            }
            //最近一次签到是昨日且之前连续未超7日
            todaySignStatus = TODAY_NOT_SIGN_IN;
        }
        //查询用户签到历史记录
        List<XZhSignInHistEntity> xZhSignInHistEntitieList = xZhSignInHistMapper.listByCustomerIdAndLimit(customerId, continuiteDay);
        for (XZhSignInHistEntity xZhSignInHistEntity : xZhSignInHistEntitieList) {
            ListSignInDetailResponse.SignInDetail signInDetail = new ListSignInDetailResponse.SignInDetail(TODAY_YES_SIGN_IN, xZhSignInHistEntity.getRewardMoney());
            signInDetailList.remove(xZhSignInHistEntity.getContinuiteDay() - 1);
            signInDetailList.add(xZhSignInHistEntity.getContinuiteDay() - 1, signInDetail);
        }

        return WrapMapper.ok(new ListSignInDetailResponse(todaySignStatus, continuiteDay, signInDetailList));
    }

    /**
     * 进行签到
     */
    @Override
    public Object postSignIn(PostSignInRequest postSignInRequest) {

        //若配置文件中未配置签到奖励则不展示签到记录
        List<Integer> signInRewardMoneyListConfigList = signInRewardMoneyListConfig.getList();
        if (CollectionUtils.isEmpty(signInRewardMoneyListConfigList)) {
            return WrapMapper.ok();
        }

        //获取session用户对象
        String customerId = postSignInRequest.getCustomerId();
        //根据customerId查询用户签到记录
        XZhSignInEntity xZhSignInEntityByCustomerId = xZhSignInMapper.findByCustomerId(customerId);
        //签到记录是否为空
        if (null == xZhSignInEntityByCustomerId) {
            XZhSignInEntity xZhSignInEntity = new XZhSignInEntity();
            xZhSignInEntity.setBuNo(UUID.randomUUID().toString());
            xZhSignInEntity.setCustomerId(customerId);
            xZhSignInEntity.setContinuiteDay(CONTINUITE_DAY_ONE);
            xZhSignInEntity.setRewardMoney(signInRewardMoneyListConfigList.get(0));
            xZhSignInEntity.setSignInDate(DateUtils.getTodayDate());
            insertSigninAndHist(xZhSignInEntity);
            return WrapMapper.ok();
        }

        long signInDateTime = xZhSignInEntityByCustomerId.getSignInDate().getTime();
        if (signInDateTime == todayDateTime) {
            return WrapMapper.error("今天已经签到");
        }

        //获取连续签到天数
        Integer continuiteDay = continuiteDay(xZhSignInEntityByCustomerId.getContinuiteDay(), signInDateTime);
        xZhSignInEntityByCustomerId.setSignInDate(DateUtils.getTodayDate());
        xZhSignInEntityByCustomerId.setContinuiteDay(continuiteDay);
        xZhSignInEntityByCustomerId.setRewardMoney(signInRewardMoneyListConfigList.get(continuiteDay - 1));
        xZhSignInEntityByCustomerId.setUpdateTime(new Date());
        xZhSignInEntityByCustomerId.setBuNo(UUID.randomUUID().toString());
        updateSignInAndInsertHist(xZhSignInEntityByCustomerId);

        return WrapMapper.ok();

    }

    private Integer continuiteDay(Integer continuiteDay, Long signInDateTime) {
        if (signInDateTime < todayDateTime) {
            return CONTINUITE_DAY_ONE;
        }
        if (continuiteDay >= continuiteDayThreshold) {
            return CONTINUITE_DAY_ONE;
        }
        return continuiteDay + 1;
    }

    private void insertSigninAndHist(XZhSignInEntity xZhSignInEntity) {
        xZhSignInMapper.insertSelective(xZhSignInEntity);
        XZhSignInHistEntity xZhSignInHistEntity = new XZhSignInHistEntity();
        BeanUtils.copyProperties(xZhSignInEntity, xZhSignInHistEntity);
        xZhSignInHistEntity.setId(null);
        xZhSignInHistMapper.insertSelective(xZhSignInHistEntity);
    }

    private void updateSignInAndInsertHist(XZhSignInEntity xZhSignInEntity) {
        xZhSignInMapper.updateByPrimaryKeySelective(xZhSignInEntity);
        XZhSignInHistEntity xZhSignInHistEntity = new XZhSignInHistEntity();
        BeanUtils.copyProperties(xZhSignInEntity, xZhSignInHistEntity);
        xZhSignInHistEntity.setId(null);
        xZhSignInHistMapper.insertSelective(xZhSignInHistEntity);
    }

}
