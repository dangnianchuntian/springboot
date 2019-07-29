/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：XmlController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.controller;

import com.zhanghan.zhboot.controller.request.XmlRequest;
import com.zhanghan.zhboot.util.wrapper.WrapMapper;
import com.zhanghan.zhboot.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "演示Xml解析控制器", tags = {"演示Xml解析控制器"})
@RestController
public class XmlController {

    @ApiOperation(value = "解析Xml", tags = {"演示Xml解析控制器"})
    @RequestMapping(value = "/analysisXml", method = RequestMethod.POST)
    public Wrapper xmlAnalysis(@RequestBody XmlRequest xmlRequest) {
        Map map = new HashMap();
        map.put("id", xmlRequest.getId());
        map.put("name", xmlRequest.getName());
        map.put("shortname", xmlRequest.getShortName());
        return WrapMapper.ok(map);
    }

}
