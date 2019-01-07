package com.zhanghan.zhboot.controller;

import com.zhanghan.zhboot.controller.request.XmlRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class XmlController {

    @RequestMapping(value = "/analysisXml", method = RequestMethod.POST)
    public Map xmlAnalysis(@RequestBody XmlRequest xmlRequest){
        Map map = new HashMap();
        map.put("id",xmlRequest.getId());
        map.put("name",xmlRequest.getName());
        map.put("shortname",xmlRequest.getShortName());
       return map;
   }

}
