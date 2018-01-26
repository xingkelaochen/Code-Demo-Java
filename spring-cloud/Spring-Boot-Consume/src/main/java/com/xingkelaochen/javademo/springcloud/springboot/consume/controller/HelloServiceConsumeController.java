package com.xingkelaochen.javademo.springcloud.springboot.consume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.xingkelaochen.javademo.springcloud.springboot.consume.service.HelloServiceConsume;
import com.xingkelaochen.javademo.springcloud.springboot.consume.vo.User;

/**
 * 演示基于Eureka服务治理的消费者操作
 * 
 * <p>
 * 	使用Ribbon客户端负载、Hystrix熔段器
 * </p>
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
@RestController
@RequestMapping(value="/helloServiceConsume")
public class HelloServiceConsumeController {

	@Autowired
	private HelloServiceConsume helloServiceConsume;
	
	@RequestMapping("hello")
	public String hello() {
		
		return helloServiceConsume.hello();
		
	}
	
	@RequestMapping(value="getUserById/{id}",method= {RequestMethod.GET})
	public User getUserById(@PathVariable String id) {
		
		// 必须初始化HystrixRequestContext，但在这里初始化是错误的，因为每次都会生成新的上下文，导致缓存并没有正确的使用，此处只是做个演示。
		HystrixRequestContext.initializeContext();
		
		return helloServiceConsume.getUserById(id);
		
	}
	
}
