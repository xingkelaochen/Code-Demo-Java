package com.xingkelaochen.javademo.springcloud.springboot.consume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/helloServiceConsume")
public class HelloServiceConsume {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 使用RestTemplate负载均衡，通过服务注册中心请求服务并返回结果
	 * @return
	 */
	@RequestMapping("hello")
	@ResponseBody
	public String hello() {
		
		// Http url并不是一个真实的地址，而是Eureka中注册的Spring Boot应用名称 
		// 由DynamicServerListLoadBalancer进行负载均衡决策请求服务
		return restTemplate.getForEntity("http://SPRING-BOOT-SERVICE/hello",String.class).getBody();
		
	}
	
}
