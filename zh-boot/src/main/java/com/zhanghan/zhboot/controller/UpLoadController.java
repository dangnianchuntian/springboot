/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：UpLoadController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.controller;

import com.zhanghan.zhboot.util.UploadUtil;
import com.zhanghan.zhboot.util.wrapper.WrapMapper;
import com.zhanghan.zhboot.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(value = "演示文件上传控制器", tags = {"演示文件上传控制器"})
public class UpLoadController {

    private static Logger logger = LoggerFactory.getLogger(UpLoadController.class);

    @Value("${upload.path}")
    private String uploadPath;

    @ApiOperation(value = "演示文件上传", tags = {"演示文件上传控制器"})
    @PostMapping(value = "/upload", consumes = "multipart/form-data", headers = "Content-Type=multipart/form-data")
    public Wrapper upload( @ApiParam(value = "文件上传", required = true,type = "file") @RequestParam("img")MultipartFile img) {

        try {
            UploadUtil.saveLocal(img, uploadPath);
        } catch (Exception e) {
            logger.error("upload exception {}",e.getMessage());
            return WrapMapper.error();
        }
        return WrapMapper.ok();
    }

}
