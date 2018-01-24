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
	 * ʹ��RestTemplate���ؾ��⣬ͨ������ע������������񲢷��ؽ��
	 * @return
	 */
	@RequestMapping("hello")
	@ResponseBody
	public String hello() {
		
		// Http url������һ����ʵ�ĵ�ַ������Eureka��ע���Spring BootӦ������ 
		// ��DynamicServerListLoadBalancer���и��ؾ�������������
		return restTemplate.getForEntity("http://SPRING-BOOT-SERVICE/hello",String.class).getBody();
		
	}
	
}
