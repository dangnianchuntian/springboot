/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战:签到奖励实现方案
 * 类名称：ListSignInDetailResponse.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhsignin.controller.response;

import lombok.Data;

import java.util.List;

@Data
public class ListSignInDetailResponse {

    //今日是否展示签到记录 true:展示;false:不展示
    private Boolean isShowSignIn = true;
    //今日是否签到 0:否;1:是
    private Integer todaySignStatus = 0;
    //连续签到天数
    private Integer continuiteDay = 0;
    //签到的金币个数
    private List<SignInDetail> signInDetailList;

    public ListSignInDetailResponse(Boolean isShowSignIn) {
        this.isShowSignIn = isShowSignIn;
    }

    public ListSignInDetailResponse(Integer todaySignStatus, Integer continuiteDay, List<SignInDetail> signInDetailList) {
        this.todaySignStatus = todaySignStatus;
        this.continuiteDay = continuiteDay;
        this.signInDetailList = signInDetailList;
    }

    @Data
    public static class SignInDetail {
        //是否签到 0:否;1:是
        private Integer signStatus = 0;
        //签到的金币个数
        private Integer rewardMoney;

        public SignInDetail(Integer signStatus, Integer rewardMoney) {
            this.signStatus = signStatus;
            this.rewardMoney = rewardMoney;
        }
    }
}
