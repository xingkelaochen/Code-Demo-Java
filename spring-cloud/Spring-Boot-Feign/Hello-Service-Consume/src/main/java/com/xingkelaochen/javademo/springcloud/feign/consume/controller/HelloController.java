package com.xingkelaochen.javademo.springcloud.feign.consume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xingkelaochen.javademo.springcloud.feign.api.dto.User;
import com.xingkelaochen.javademo.springcloud.feign.consume.service.RefactorHelloService;

@RestController
public class HelloController {

	@Autowired
	private RefactorHelloService refactorHelloService;
	
	@RequestMapping(value="/hello",method= {RequestMethod.GET})
	public String hello() {
		
		return refactorHelloService.hello(new User("test1","xingkelaochen","ÄÐ",33));
		
	}
	
}
