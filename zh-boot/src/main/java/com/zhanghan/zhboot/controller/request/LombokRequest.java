package com.zhanghan.zhboot.controller.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("Lombok演示请求实体")
@Data
public class LombokRequest {

    @ApiModelProperty(value = "整数类型")
    private Integer intLombok;
    @ApiModelProperty(value = "字符串类型")
    private String strLombok;
    @ApiModelProperty(value = "布尔类型")
    private Boolean boleanLombok;
    @ApiModelProperty(value = "实体类型")
    private Person personLombok;

    @Data
    private static class Person {
        private Integer age;
        private String name;
    }


}
