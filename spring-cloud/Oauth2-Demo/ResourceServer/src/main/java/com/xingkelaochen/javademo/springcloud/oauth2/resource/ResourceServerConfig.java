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
		
		// ����ʹ��OAuth-Aware����ʽ��������OAuth-Aware Expression Handler����������ط��ʽӿڵ�Oauth��Ȩ��֤��
		
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.anyRequest().access("#oauth2.clientHasRole('test')");
	}

}