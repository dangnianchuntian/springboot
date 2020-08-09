/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战分页查询附近的人: Redis+GeoHash+Lua
 * 类名称：GeoController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhnearbypeople.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhanghan.zhnearbypeople.controller.request.ListNearByPeopleRequest;
import com.zhanghan.zhnearbypeople.controller.request.PostGeoRequest;
import com.zhanghan.zhnearbypeople.service.GeoService;

@RestController
public class GeoController {

    @Autowired
    private GeoService articleCountService;

   /**
    * 记录用户访问记录
    */
    @RequestMapping(value = "/post/geo", method = RequestMethod.POST)
    public Object postGeo(@RequestBody @Validated PostGeoRequest postGeoRequest) {
        return articleCountService.postGeo(postGeoRequest);
    }
    /**
     *  批量将缓存中的数据同步到MySQL（模拟定时任务操作）
     */

    @RequestMapping(value = "/list/nearby/people", method = RequestMethod.POST)
    public Object listNearByPeople(@RequestBody @Validated ListNearByPeopleRequest listNearByPeopleRequest) {
        return articleCountService.listNearByPeople(listNearByPeopleRequest);
    }

}
