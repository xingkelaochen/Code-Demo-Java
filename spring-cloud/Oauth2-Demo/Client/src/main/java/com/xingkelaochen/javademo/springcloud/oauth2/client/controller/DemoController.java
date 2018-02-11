package com.xingkelaochen.javademo.springcloud.oauth2.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest")
public class DemoController {

	@Autowired
	private OAuth2RestTemplate oAuth2RestTemplate;
	
	@RequestMapping(value="sayHello")
	public String sayHello() {
		
		return oAuth2RestTemplate.getForEntity("http://localhost:10001//sayHello", String.class).getBody();
		
	}
	
}
