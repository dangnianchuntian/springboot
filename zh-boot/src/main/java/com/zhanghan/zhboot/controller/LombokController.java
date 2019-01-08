package com.zhanghan.zhboot.controller;

import com.zhanghan.zhboot.controller.request.LombokRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LombokController {

    @RequestMapping(value = "/lombok", method = RequestMethod.POST)
    public Map lombok(@RequestBody LombokRequest lombokRequest) {
        System.out.println(lombokRequest.toString());
        Map<String, Object> map = new HashMap();
        map.put("intLombok", lombokRequest.getIntLombok());
        map.put("strLombok", lombokRequest.getStrLombok());
        map.put("boleanLombok", lombokRequest.getBoleanLombok());
        map.put("mapLombok", lombokRequest.getMapLombok());
        return map;
    }

}
