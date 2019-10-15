# lvcloud-rate-limiter(dubbo限流组件)

   ## module介绍：
   ./限流架构.png
   
   1、lvcloud-limiter-core:
   2、lvcloud-limiter-client:
   3、lvcloud-limiter-server:
     
   ### 组件引用说明：
   1、引入pom
   	<dependency>
   		  <groupId>com.lv.cloud</groupId>
   		  <artifactId>lvcloud-limiter-client</artifactId>
   		  <version>1.0.0-SNAPSHOT</version>
   	 </dependency>
   	 
   2、加注解@@EnableRateLimiter
   	  
   3、还需引入lvcloud-rate-limiter.xml到项目classes中
     1) 此文件是限流接口配置文件，配置如下：
   	<?xml version="1.0" encoding="UTF-8"?>
   	<methods>
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
   	    <method>
   	    	<name>interface/method</name>
   	    	<type>1</type>
   	    	<permits>5</permits>
   	    </method>
   	</methods>