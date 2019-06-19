# 实战SpringBoot
  
# 1.Lombok

* 代码版本: 1.0.0-Release

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

* 博客地址：[使用Lombok简化你的代码](https://blog.csdn.net/zhanghan18333611647/article/details/86095583 "别忘记点赞哦") 



# 2.SpringMvc解析XML

* 代码版本: 1.0.0-Release

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

* 博客地址：[SpringMvc接收xml请求](https://blog.csdn.net/zhanghan18333611647/article/details/86098569 "别忘记点赞哦")


# 3.SpringBoot Mybatis 添加多数据源

* 代码版本: 1.0.0-Release

* 功能：SpringBoot一个项目添加多个数据源，数据库连接池用的是高性能的HikariCP(springboot2.0以后的默认数据库连接池)

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

* 博客地址：[SpringBoot整合Mybatis配置多数据源](https://blog.csdn.net/zhanghan18333611647/article/details/86363865 "别忘记点赞哦") 



# 4.SpringBoot整合Redis

* 代码版本: 1.0.0-Release

* 功能：SpringBoot中集成Redis操作

* 包含模块：  
    * RedisProperties：读取配置文件中配置Redis的属性值
    * RedisConfig：建立操作Redis的Template
    * OrderServiceImpl：演示效果的Service
    * RedisController：演示操作Redis效果   
    
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/get/redis
 

* 博客地址：[SpringBoot整合Redis](https://blog.csdn.net/zhanghan18333611647/article/details/86366865 "别忘记点赞哦") 

# 5.SpringBoot增加健康检查

* 代码版本: 1.0.0-Release

* 功能：SpringBoot中增加健康检查

* 包含模块：  
    * Pom：增加spring-boot-starter-actuator依赖
    * application.properties：根据Spring Boot的版本（2.0前还是2.0后）增加相关的配置
  
    
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://127.0.0.1:8080/health
 

* 博客地址：[SpringBoot配置健康检查与监控](https://blog.csdn.net/zhanghan18333611647/article/details/89068368 "别忘记点赞哦") 


# 6.Redis Pipeline 轻松实现百倍性能提升

* 代码版本: 1.0.0-Release

* 功能：增加Redis Pipeline实现方式

* 包含模块：  
    * RedisController：增加Redis Pipeline 操作Redis
  
    
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://127.0.0.1:8080/add/pipeline 和 http://127.0.0.1:8080/add/single
 

* 博客地址：[Redis Pipeline 轻松实现百倍性能提升](https://blog.csdn.net/zhanghan18333611647/article/details/90646951 "别忘记点赞哦") 


# 7.优雅替代if判断；提高系统的扩展性

* 代码版本: 1.0.0-Release

* 功能：优雅替代if判断；提高系统的扩展性

* 包含模块：  
    * CheckMobileController：演示校验手机号前缀的两种方式
    * MobilePreFixProperties：从配置文件中读取配置国家和手机号前缀的对应关系
    * application：增加国家和手机前缀对应关系
  
    
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://127.0.0.1:8080/bad/check/mobile 和 http://127.0.0.1:8080/good/check/mobile
 

* 博客地址：[替代if的优雅方案，提高程序扩展性](https://blog.csdn.net/zhanghan18333611647/article/details/91603890 "别忘记点赞哦") 



# 8.SpringBoot集成Swagger

* 代码版本: 1.1.0-Release

* 功能：SpringBoot集成Swagger

* 包含模块：  
    * Pom：增加Swagger的依赖
    * SwaggerConfig：配置生效Swagger
    * 相应的Controller以及Request实体：增加Swagger的注释
    * application：增加是否启用swagger开关（一般生产环境关闭swagger防止被其他人扫描，测试和开发环境打开便于联调）
  
    
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/swagger-ui.html#/
 

* 博客地址：[SpringBoot集成Swagger](https://blog.csdn.net/zhanghan18333611647/article/details/91900759 "别忘记点赞哦") 
