/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：SpringBoot项目接入ELK
 * 类名称：RecordExcepitonLogController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhelkboot.controller;

import com.zhanghan.zhelkboot.controller.request.RecordExceptionLogRequest;
import com.zhanghan.zhelkboot.service.RecordExcepitonLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordExcepitonLogController {

    @Autowired
    private RecordExcepitonLogService recordExcepitonLogService;

    @RequestMapping(value = "/record/exception/log", method = RequestMethod.POST)
    public Object recordExcepitonLog(@RequestBody RecordExceptionLogRequest recordExceptionLogRequest) {
        return recordExcepitonLogService.recordExcepitonLog(recordExceptionLogRequest);
    }

}
