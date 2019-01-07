package com.zhanghan.zhboot.controller.request;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serializable;

@JacksonXmlRootElement(localName = "school")
@Data
public class XmlRequest implements Serializable {

    @JacksonXmlProperty(localName = "id")
    private Integer id;
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "shortname")
    private String shortName;


}
