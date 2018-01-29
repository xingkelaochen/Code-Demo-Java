package com.xingkelaochen.javademo.springcloud.feign.consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.Logger;

/**
 * 在Spring Cloud的服务消费端中使用Ribbon与Hystrix负责负载均衡与断路器。
 * 整合方法是：使用@LoadBalanced标注RestTemplate开启负载功能；使用@EnableCircuitBreaker支持断路器功能，并在方法上标注@HystrixCommand开启断路器。
 * 此示例演示使用@Feign简洁整合上述两种功能的方法，首先使用@EnableFeignClients开启对Feign的支持。
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
@EnableDiscoveryClient
@EnableFeignClients
public class Application {

	/**
	 * Feign为每个客户端都自动开启一个日志，默认级别为INFO，可以通过申明的方式进行配置。（也可以在@FeignClient中使用confinuration进行单独配置类的指定。）
	 * @return
	 */
	@Bean
	public Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
