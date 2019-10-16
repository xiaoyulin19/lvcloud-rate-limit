# lvcloud-rate-limiter(dubbo限流组件)

   ## module介绍：
   ./限流架构.png
   
   1、lvcloud-limiter-core:限流核心服务
   2、lvcloud-limiter-client:客户端，限流filter（spring boot版本）
   3、lvcloud-limiter-server:限流管理控制台
   
   渠道限流说明:
   
       渠道限流接口已提供，支持分布式（redis）和本地限流两种模式： 
       1、非分布式，则调取com.lv.cloud.ratelimiter.service.LocalRateLimiter.isLimited(String method, String distributorChannel)，判断是否被限流
       2、分布式，则调取com.lv.cloud.ratelimiter.service.JedisClusterRateLimiter.isLimited(String method, String distributorChannel)，判断是否被限流
     
   ### 组件引用说明：
   1、引入pom
   
   	<dependency>
   		  <groupId>com.lv.cloud</groupId>
   		  <artifactId>lvcloud-limiter-client</artifactId>
   		  <version>1.0.0-SNAPSHOT</version>
   	 </dependency>
   	 
   2、加注解@@EnableRateLimiter
      
   3、分布式限流使用配置：
   
    引入配置项： lvcloud.rate.limiter.mode=1 （默认0：本地限流，同时支持本地&分布式： lvcloud.rate.limiter.mode=2） 
    同时引入redis-cluster配置：
    ###redis###
    lvcloud.jedis.enable=true
    lvcloud.jedis.server=10.200.4.76:6379,10.200.4.75:6379,10.200.4.74:6379
    lvcloud.jedis.timeout=2000
    lvcloud.jedis.maxRedirections=6
    
    lvcloud.jedis.maxWaitMillis=-1
    lvcloud.jedis.maxTotal=1000
    lvcloud.jedis.minIdle=8
    lvcloud.jedis.maxIdle=100
   	  
   4、还需引入lvcloud-rate-limiter.xml到项目classes中
     1) 此文件是限流接口配置文件，配置如下：
   
    <?xml version="1.0" encoding="UTF-8"?>
   	<methods>
   	    <!-- 分布式限流-->
   	    <method>
   	    	<name>interface/method</name>
   	    	<type>2</type>
   	    	<permits>5</permits>
   	    	<timeunit>SECONDS</timeunit>
   	    </method>
   	    <method>
   	    	<name>interface/method</name>
   	    	<type>2</type>
   	    	<permits>5</permits>
   	    	<timeunit>MINUTES</timeunit>
   	    </method>
   	    <!-- 本地限流-->
   	    <method>
   	    	<name>interface/method</name>
   	    	<type>1</type>
   	    	<permits>5</permits>
   	    </method>
   	</methods>