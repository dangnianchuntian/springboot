package com.zhanghan.zhboot.controller.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("演示多数据源请求实体")
@Data
public class OrderRequest {

    @ApiModelProperty(value = "手机号")
    private String mobile;


}
