package com.zhanghan.zhsignin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "zh.sign.in.reward.money")
@Data
public class SignInRewardMoneyListConfig {
    /**
     * 连续天数集合
     */
    private List<Integer> list;
}
