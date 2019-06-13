package com.zhanghan.zhboot.controller;

import com.zhanghan.zhboot.controller.request.LombokRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "演示Lombok控制器",tags = {"演示Lombok控制器"})
public class LombokController {

    @ApiOperation(value="优雅校验手机号格式方式",tags = {"演示Lombok控制器"})
    @RequestMapping(value = "/lombok", method = RequestMethod.POST)
    public Map lombok(@RequestBody LombokRequest lombokRequest) {
        System.out.println(lombokRequest.toString());
        Map<String, Object> map = new HashMap();
        map.put("intLombok", lombokRequest.getIntLombok());
        map.put("strLombok", lombokRequest.getStrLombok());
        map.put("boleanLombok", lombokRequest.getBoleanLombok());
        map.put("personLombok", lombokRequest.getPersonLombok());
        return map;
    }

}
