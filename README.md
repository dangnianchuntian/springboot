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


# 3.SpringBoot Mybatis 添加多数据源

* 功能：SpringBoot一个项目添加多个数据源，数据库连接池用的是高性能的Hikari(springboot2.0以后的默认数据库连接池)

* 包含模块：  
    * OrderRequest：演示使用SpringMvc接收mobile参数
    * OrderController：演示效果的Controller
    * OrderServiceImpl：演示效果的Service
    * UserInfoMapper和OrderInfoMapper：演示效果的Mapper
    * UserDataSourceProperties和OrderDataSourceProperties：读取配置文件中配置的数据源属性值
    * UserDataSourceConfig和OrderDataSourceConfig：数据库操作    
    
* 启动项目并验证
    * 初始化数据库:执行script/db下的zh_order.sql和zh_user.sql
    * 启动zh-boot
    * 访问 http://localhost:8080/get/order/borrow
    * 参数：
    ```xml
     {
         "mobile":"17633201809"
     }
    ```     

* 博客地址：[Java基础（十五）:SpringBoot、Mybatis配置多数据源](https://blog.csdn.net/zhanghan18333611647/article/details/86363865 "别忘记点赞哦") 



# 4.SpringBoot整合Redis

* 功能：SpringBoot中集成Redis操作

* 包含模块：  
    * RedisProperties：读取配置文件中配置Redis的属性值
    * RedisConfig：建立操作Redis的Template
    * OrderServiceImpl：演示效果的Service
    * RedisController：演示操作Redis效果   
    
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/get/redis
 

* 博客地址：[Java基础（十六）:SpringBoot整合Redis](https://blog.csdn.net/zhanghan18333611647/article/details/86366865 "别忘记点赞哦") 
