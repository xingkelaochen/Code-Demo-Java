package com.xingkelaochen.javademo.springcloud.feign.consume.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xingkelaochen.javademo.springcloud.feign.api.dto.User;

/**
 * 
 * 使用@FeignClient(value="Hello-Service")指定在Eureka中注册的服务，开启对HelloService接口的支持。
 * 使用Feign进行消费端的申明，方法是需要使用MVC的@RequestMapping进行绑定，但因为此接口继承了HelloService接口，所以此接口不用申明任何方法。
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
@FeignClient(value="HELLO-SERVICE",fallback=HelloServiceFallback.class)
public interface RefactorHelloService {

	@RequestMapping("hello")
	public String hello(@RequestBody User user);
	
}
