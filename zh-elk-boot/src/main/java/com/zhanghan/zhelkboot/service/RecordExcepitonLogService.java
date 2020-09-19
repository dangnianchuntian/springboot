/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：SpringBoot项目接入ELK
 * 类名称：RecordExcepitonLogService.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhelkboot.service;


import com.zhanghan.zhelkboot.controller.request.RecordExceptionLogRequest;

public interface RecordExcepitonLogService {

    Object recordExcepitonLog(RecordExceptionLogRequest recordExceptionLogRequest);

}
