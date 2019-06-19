/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：XmlRequest.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://blog.csdn.net/zhanghan18333611647
 */

package com.zhanghan.zhboot.controller.request;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("演示Xml解析请求实体")
@JacksonXmlRootElement(localName = "school")
@Data
public class XmlRequest {

    @ApiModelProperty(value = "xml中的id结点",required = true)
    @JacksonXmlProperty(localName = "id")
    private Integer id;
    @ApiModelProperty(value = "xml中的name结点",required = true)
    @JacksonXmlProperty(localName = "name")
    private String name;
    @ApiModelProperty(value = "xml中的shortname结点",required = true)
    @JacksonXmlProperty(localName = "shortname")
    private String shortName;


}
