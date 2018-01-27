package com.xingkelaochen.javademo.springcloud.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Hystrix的仪表盘用来监控其各项指标信息。
 * 集成Hystrix的服务消费端使用@EnableCircuitBreaker开启断路器功能，使用actuator提供一个/hystrix.stream端口供hystrix-dashboard进行展示。
 * 在本应用启动后的/hystrix页面输入对应/hystrix.stream地址就可以查看图形化的展示界面。
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
@EnableHystrixDashboard
public class Application {

	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
}
