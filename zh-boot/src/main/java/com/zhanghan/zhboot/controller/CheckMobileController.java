package com.zhanghan.zhboot.controller;

import com.mysql.jdbc.StringUtils;
import com.zhanghan.zhboot.controller.request.MobileCheckRequest;
import com.zhanghan.zhboot.properties.MobilePreFixProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "校验手机号控制器",tags = {"校验手机号控制器"})
public class CheckMobileController {

    @Autowired
    private MobilePreFixProperties mobilePreFixProperties;

    @ApiOperation(value="优雅校验手机号格式方式",tags = {"校验手机号控制器"})
    @RequestMapping(value = "/good/check/mobile", method = RequestMethod.POST)
    public Map goodCheckMobile(@RequestBody @Validated MobileCheckRequest mobileCheckRequest) {

        String countryCode = mobileCheckRequest.getCountryCode();
        String proFix = mobilePreFixProperties.getPrefixs().get(countryCode);

        if (StringUtils.isNullOrEmpty(proFix)) {
            return buildFailResponse();
        }

        String mobile = mobileCheckRequest.getMobile();

        Boolean isLegal = false;
        if (mobile.startsWith(proFix)) {
            isLegal = true;
        }


        Map map = new HashMap();
        map.put("code", 0);
        map.put("mobile", mobile);
        map.put("isLegal", isLegal);
        map.put("proFix", proFix);
        return map;
    }

    @ApiOperation(value="扩展性差校验手机号格式方式",tags = {"校验手机号控制器"})
    @RequestMapping(value = "/bad/check/mobile", method = RequestMethod.POST)
    public Map badCheckMobile(@RequestBody MobileCheckRequest mobileCheckRequest) {

        String countryCode = mobileCheckRequest.getCountryCode();

        String proFix;
        if (countryCode.equals("CN")) {
            proFix = "86";
        } else if (countryCode.equals("US")) {
            proFix = "1";
        } else {
            return buildFailResponse();
        }

        String mobile = mobileCheckRequest.getMobile();

        Boolean isLegal = false;
        if (mobile.startsWith(proFix)) {
            isLegal = true;
        }


        Map map = new HashMap();
        map.put("code", 0);
        map.put("mobile", mobile);
        map.put("isLegal", isLegal);
        map.put("proFix", proFix);
        return map;
    }

    private Map buildFailResponse() {
        Map map = new HashMap();
        map.put("code", 1);
        map.put("mobile", "");
        map.put("isLegal", false);
        map.put("proFix", "");
        return map;
    }


}
