package com.zhanghan.zhboot.controller.request;


import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class MobileCheckRequest {

    @NotNull
    private String countryCode;

    @NotNull
    private String mobile;


}
