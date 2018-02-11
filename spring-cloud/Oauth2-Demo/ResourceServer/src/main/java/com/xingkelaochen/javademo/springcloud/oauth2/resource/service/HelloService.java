package com.xingkelaochen.javademo.springcloud.oauth2.resource.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {

	@RequestMapping(value="/sayHello")
	public String sayHello() {
		return "Hello World!";
	}
	
}
