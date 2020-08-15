# otter-spring-boot-starter

水獭   SpringBoot 版  

[English](./README.md)  

支持功能：  
- [x] 确保调用一致性  

# 确保调用一致性：  
  支持SpringBoot,SpringCloud(feign),dubbo-spring-boot-starter,butterfly-spring-boot-starter框架  
  使用之前,请确保调用链路,为幂等性.   
  简单讲解:服务A调用服务B,服务B调用服务C,链路中,B与C执行成功,  
  A执行异常,这时则出现数据不一致.    
  水獭则会将错误信息保存在MySQL,并自动尝试重新调用链路,  
  服务A调用服务B,服务B调用服务C.    
  链路调用成功,则数据保持一致性.    
  
## Quick Start  

```xml
<!--在pom.xml中添加依赖-->
        <dependency>
            <artifactId>otter-spring-boot-starter</artifactId>
            <groupId>com.github.thierrysquirrel</groupId>
            <version>1.1.7-RELEASE</version>
        </dependency>
```  

 ### 配置文件  
 
 ```properties
 ## application.properties
spring.datasource.url= #MySQL数据库Url
spring.datasource.username= #MySQL数据库username
spring.datasource.password= #MySQL数据库password
spring.jpa.hibernate.ddl-auto= #如果未初始化otter_entity数据库,请设置:update
 ```  

# 开始使用  

```java
@RestController
@Otter
public class Demo {
	
    @Resource
	public FeignService feignService;
    /**
    * 使用zookeeper做为注册中心,作为演示
    */
	@Reference
	private DubboService dubboService;

    @Resource
    private ButterService butterService;

	@Repair
	@GetMapping("/feign")
	public String feign() {
        feignService.update();
        //本地SQL执行
		return "feign";
	}

    @Repair
	@GetMapping("/dubbo")
	public String dubbo() {
        dubboService.update();
        //本地SQL执行
		return "dubbo";
	}

    @Repair
	@GetMapping("/butter")
	public String butter() {
        butterService.update();
        //本地SQL执行
		return "butter";
	}
}
```