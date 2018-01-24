package com.xingkelaochen.javademo.springcloud.springboot.service.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class HelloController {

	private static Log log = LogFactory.getLog(HelloController.class);
	
	@RequestMapping(value="hello")
	public String hello() {
		
		return "hello world!";
		
	}
	
}
