package com.xingkelaochen.javademo.springcloud.oauth2.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		// 可以使用OAuth-Aware表达式处理器（OAuth-Aware Expression Handler），进行相关访问接口的Oauth授权验证。
		
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.anyRequest().access("#oauth2.clientHasRole('test')");
	}

}
