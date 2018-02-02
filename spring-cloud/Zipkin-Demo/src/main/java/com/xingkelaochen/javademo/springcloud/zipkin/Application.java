package com.xingkelaochen.javademo.springcloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

/**
 * Spring Boot与Zipkin整合，为Spring Cloud微服务的调用链路提供跟踪功能。
 * 需要在微服务中依赖spring-cloud-sleuth-zipkin，并使用spring.zipkin.base-url设置zipkin整合服务的访问地址
 *
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
@EnableZipkinServer
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
