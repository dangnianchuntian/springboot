package com.zhanghan.zhboot.controller.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("手机号校验请求实体")
@Data
public class MobileCheckRequest {

    @ApiModelProperty(value = "国家编码",required = true)
    @NotNull
    private String countryCode;

    @ApiModelProperty(value = "手机号",required = true)
    @NotNull
    private String mobile;


}
