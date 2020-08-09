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

* 博客地址：[使用Lombok简化你的代码](https://zhanghan.blog.csdn.net/article/details/86095583 "别忘记点赞哦") 



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

* 博客地址：[SpringMvc接收xml请求](https://zhanghan.blog.csdn.net/article/details/86098569 "别忘记点赞哦")


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

* 博客地址：[SpringBoot整合Mybatis配置多数据源](https://zhanghan.blog.csdn.net/article/details/86363865 "别忘记点赞哦") 



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
 

* 博客地址：[SpringBoot整合Redis](https://zhanghan.blog.csdn.net/article/details/86366865 "别忘记点赞哦") 

# 5.SpringBoot增加健康检查

* 代码版本: 1.0.0-Release

* 功能：SpringBoot中增加健康检查

* 包含模块：  
    * Pom：增加spring-boot-starter-actuator依赖
    * application.properties：根据Spring Boot的版本（2.0前还是2.0后）增加相关的配置
  
    
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://127.0.0.1:8080/health
 

* 博客地址：[SpringBoot配置健康检查与监控](https://zhanghan.blog.csdn.net/article/details/89068368 "别忘记点赞哦") 


# 6.Redis Pipeline 轻松实现百倍性能提升

* 代码版本: 1.0.0-Release

* 功能：增加Redis Pipeline实现方式

* 包含模块：  
    * RedisController：增加Redis Pipeline 操作Redis
  
    
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://127.0.0.1:8080/add/pipeline 和 http://127.0.0.1:8080/add/single
 

* 博客地址：[Redis Pipeline 轻松实现百倍性能提升](https://zhanghan.blog.csdn.net/article/details/90646951 "别忘记点赞哦") 


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
 

* 博客地址：[替代if的优雅方案，提高程序扩展性](https://zhanghan.blog.csdn.net/article/details/91603890 "别忘记点赞哦") 


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
 

* 博客地址：[SpringBoot集成Swagger](https://zhanghan.blog.csdn.net/article/details/91900759 "别忘记点赞哦") 


# 9.标准化json返回值

* 代码版本: 1.2.0-Release

* 功能：SpringBoot 规范返回值

* 包含模块：  
    * WrapMapper：统一返回值格式操作工具类
    * Wrapper：统一返回值格式类
    * 相应的Controller：修改返回值类型
          
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/swagger-ui.html#/
 

* 博客地址：[标准化json返回值](https://zhanghan.blog.csdn.net/article/details/92845986 "别忘记点赞哦") 


# 10.统一异常处理

* 代码版本: 1.3.0-Release

* 功能：SpringBoot 添加统一异常处理

* 包含模块：  
    * GlobalExceptionHandler：拦截相关异常
          
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/swagger-ui.html#/
 

* 博客地址：[统一异常处理](https://zhanghan.blog.csdn.net/article/details/93235833 "别忘记点赞哦") 


# 11.图片上传

* 代码版本: 1.4.0-Release

* 功能：SpringBoot 添加图片上传

* 包含模块：  
    * MultipartConfig：文件统一配置类
    * UpLoadController：图片上传controller    
          
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/swagger-ui.html#/
 

* 博客地址：[图片上传](https://zhanghan.blog.csdn.net/article/details/94411236 "别忘记点赞哦") 


# 12.金额校验

* 代码版本: 1.4.0-Release

* 功能：金额校验

* 包含模块：  
    * MoneyUtil：金额校验工具类
    * CheckMoneyController：检验金额controller    
          
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/swagger-ui.html#/
 

* 博客地址：[BigDecimal 金额校验](https://zhanghan.blog.csdn.net/article/details/97649212 "别忘记点赞哦") 


# 13.统一拦截器

* 代码版本: 1.4.0-Release

* 功能：统一日志拦截器

* 包含模块：  
    * InterceptController：拦截的控制器
    * InterceptRequest：拦截的请求体
    * InterceptLog：日志拦截器
    * WebMvcConfig：容器中加载日志拦截器
    * BodyReaderWrapper：请求体封装类
    * BodyReaderFilter：过滤器
          
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/swagger-ui.html#/
 

* 博客地址：[Required request body is missing](https://blog.csdn.net/zhanghan18333611647/article/details/98673078 "别忘记点赞哦") 


# 14.增加Spring Boot Admin监控 & 规范日志输出格式

* 代码版本: 1.5.0-Release

* 功能：增加Spring Boot Admin监控、规范日志输出格式

* 包含模块：  
    * zh-monitor项目：新增监控项目
    * zh-boot项目: 增加SpringBootAdmin的client依赖，增加logback.xml规范日志输出格式
          
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/swagger-ui.html#/
    * 启动zh-monitor
    * 访问 http://localhost:8081

* 博客地址：
    * [logback 日志输出格式](https://zhanghan.blog.csdn.net/article/details/101269545 "别忘记点赞哦") 
    * [SpringBoot实战（十二）:集成 Spring Boot Admin 监控](https://zhanghan.blog.csdn.net/article/details/101273915 "别忘记点赞哦") 
    * [SpringBoot实战（十三）:Spring Boot Admin 动态修改日志级别](https://zhanghan.blog.csdn.net/article/details/101282082 "别忘记点赞哦") 


# 15.集成安全和告警模块

* 代码版本: 1.6.0-Release

* 功能：增加Spring Boot Admin安全认证以及告警

* 包含模块：  
    * zh-monitor项目：安全认证告警
    * zh-boot项目: 增加安全认证配置
          
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/swagger-ui.html#/
    * 启动zh-monitor
    * 访问 http://localhost:8081

* 博客地址：
    * [SpringBoot实战（十四）:Spring Boot Admin 集成安全模块](https://zhanghan.blog.csdn.net/article/details/101595226 "别忘记点赞哦") 
    * [SpringBoot实战（十五）:Spring Boot Admin 集成告警模块](https://zhanghan.blog.csdn.net/article/details/101597346 "别忘记点赞哦")  

# 16.集成日志收集系统

* 代码版本: 1.7.0-Release

* 功能：增加request及response日志拦截

* 包含模块：      
    * zh-boot项目: 增加请求与响应的日志拦截
          
* 启动项目并验证
    * 启动zh-boot
    * 访问 http://localhost:8080/swagger-ui.html#/    

* 博客地址：
    * [从零学ELK系列](https://blog.csdn.net/u012829124/category_9628434.html "别忘记点赞哦") 
    
# 17.Spring Boot实战解决高并发数据入库: Redis 缓存+MySQL 批量入库

* 代码版本: 1.8.0-Release

* 功能：
    * 缓存记录PV，UV
    * 将缓存中记录同步到MySQL
    
* 技术栈：  
    * SpringBoot
    * Redis
    * MySQL
          
* 启动项目并验证
    * 启动zh-redis-to-db    

* 博客地址：
    * [Spring Boot实战解决高并发数据入库: Redis 缓存+MySQL 批量入库](https://zhanghan.blog.csdn.net/article/details/107878941 "别忘记点赞哦")


# 18.Spring Boot实战分页查询附近的人: Redis+GeoHash+Lua

* 代码版本: 1.9.0-Release

* 功能：
    * 记录地理位置
    * 分页查询附近的人
    
* 技术栈：  
    * SpringBoot
    * Redis(version>=3.2)
          
* 启动项目并验证
    * 启动zh-nearby-people    

* 博客地址：
    * [Spring Boot实战分页查询附近的人: Redis+GeoHash+Lua](https://blog.csdn.net/zhanghan18333611647/article/details/107897938 "别忘记点赞哦")
