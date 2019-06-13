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
