package com.xingkelaochen.codedemo.springcloud.euraka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka服务注册中心应用
 *
 * <p>
 * 	客户端通过REST方式请求注册中心进行服务注册、服务签约（销毁）、服务获取、心跳等操作。
 * </p>
 * <p>
 * 	服务注册到注册中心将维护一个双层Map数据结构，第一层Map的Key为服务名称，第二层Map的Key为服务实例名称（InstanceInfo中的instanceId值）
 * </p>
 *
 * @author xingkelaochen
 * 
 * <p>
 * E-MAIL: admin@xingkelaochen.com
 * <br />
 * SITE: http://www.xingkelaochen.com
 * </p>
 *
 */
@SpringBootApplication
// 使用@EnableEurekaServer标注开启应用Eureka服务注册中心的支持
@EnableEurekaServer
public class Application {
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
		
	}
	
}
