/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：MoneyUtil.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.util;

import java.util.regex.Pattern;

public class MoneyUtil {

    //两位小数金额校验
    public static boolean judgeTwoDecimal(Object obj) {
        boolean flag = false;
        try {
            if (obj != null) {
                String source = obj.toString();
                // 判断是否是整数或者是携带一位或者两位的小数
                Pattern pattern = Pattern.compile("^[+]?([0-9]+(.[0-9]{1,2})?)$");
                if (pattern.matcher(source).matches()) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return flag;
    }
}
