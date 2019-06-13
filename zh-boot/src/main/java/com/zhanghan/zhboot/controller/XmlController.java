package com.zhanghan.zhboot.controller;

import com.zhanghan.zhboot.controller.request.XmlRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "演示Xml解析控制器",tags = {"演示Xml解析控制器"})
@RestController
public class XmlController {

    @ApiOperation(value="解析Xml",tags = {"演示Xml解析控制器"})
    @RequestMapping(value = "/analysisXml", method = RequestMethod.POST)
    public Map xmlAnalysis(@RequestBody XmlRequest xmlRequest){
        Map map = new HashMap();
        map.put("id",xmlRequest.getId());
        map.put("name",xmlRequest.getName());
        map.put("shortname",xmlRequest.getShortName());
       return map;
   }

}
