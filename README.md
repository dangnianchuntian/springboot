# 实战SpringBoot
  
# 1.Lombok

* 功能：使用Lombok简化代码

* 包含模块：  
    * LombokRequest：演示使用Lombok简化代码
    * LombokController：演示效果
    
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/lombok
    * 参数：
    ```json
      {
        "intLombok":3,
        "strLombok":"zhanghan",
        "boleanLombok":true,
        "personLombok":{
          "name":"zhangsan",
          "age":15
        }
      }
    ```

* 博客地址：[Java基础（十三）:使用Lombok简化你的代码](https://blog.csdn.net/zhanghan18333611647/article/details/86095583 "别忘记点赞哦") 



# 2.SpringMvc解析XML

* 功能：SpringMvc解析xml参数请求

* 包含模块：  
    * XmlRequest：演示使用SpringMvc接收xml参数
    * XmlController：演示效果
    
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/analysisXml
    * 参数：
    ```xml
      <school>
	       <id>1111</id>
	       <name>沙河市第一高级中学</name>
	       <shortname>沙河一中</shortname>
      </school>
    ```     

* 博客地址：[Java基础（十四）:SpringMvc接收xml请求](https://blog.csdn.net/zhanghan18333611647/article/details/86098569 "别忘记点赞哦") 
