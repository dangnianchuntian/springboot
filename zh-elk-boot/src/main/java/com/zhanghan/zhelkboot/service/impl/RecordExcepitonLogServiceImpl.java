/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：SpringBoot项目接入ELK
 * 类名称：RecordExcepitonLogServiceImpl.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhelkboot.service.impl;

import com.zhanghan.zhelkboot.controller.request.RecordExceptionLogRequest;
import com.zhanghan.zhelkboot.service.RecordExcepitonLogService;
import com.zhanghan.zhelkboot.util.wrapper.WrapMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecordExcepitonLogServiceImpl implements RecordExcepitonLogService {

    /**
     * Redis批量Set
     */
    @Override
    public Object recordExcepitonLog(RecordExceptionLogRequest recordExceptionLogRequest) {
        Integer divisor = recordExceptionLogRequest.getDivisor();
        int consult = 2 / divisor;
        Map<String, Object> map = new HashMap();
        map.put("consult", consult);
        return WrapMapper.ok(map);
    }

}
