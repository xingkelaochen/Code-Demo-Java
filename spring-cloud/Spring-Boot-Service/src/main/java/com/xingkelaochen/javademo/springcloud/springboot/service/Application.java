package com.xingkelaochen.javademo.springcloud.springboot.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Eureka服务的提供者应用
 * 
 * <p>
 * 	使用@EnableDiscoveryClient，通过eureka.client.service-url.defaultZone配置，将主要负责以下几点与Eureka Server的操作：
 * 	1. 负责向注册中心注册自己
 *  2. 向注册中心租约
 *  3. 应用正常关闭时，向注册中心取消租约
 *  4. 查询注册中心的服务列表
 *  （上述所有交互都是通过Rest方式进行的）
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
// 使用@EnableEurekaClient标注此应用为Eureka客户端应用，将提供服务。
@EnableEurekaClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
