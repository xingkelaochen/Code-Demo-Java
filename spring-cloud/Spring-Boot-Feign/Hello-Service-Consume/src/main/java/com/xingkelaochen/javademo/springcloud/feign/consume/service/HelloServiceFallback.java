package com.xingkelaochen.javademo.springcloud.feign.consume.service;

import org.springframework.stereotype.Component;

import com.xingkelaochen.javademo.springcloud.feign.api.dto.User;

/**
 * �����쳣���񽵼�����������ʹ��@HystrixCommandע��ķ�ʽ�У�ʹ��fallbackMethod����ָ��ͬ���еķ������ƣ�����ʵ�ַ�ʽ��Ҫ��@Feignע����ʹ��fallbackָ������
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
@Component
public class HelloServiceFallback implements RefactorHelloService {

	@Override
	public String hello(User user) {
		
		return "error";
	}

}
