package com.xingkelaochen.javademo.springcloud.feign.consume.service;

import com.xingkelaochen.javademo.springcloud.feign.api.dto.User;
import com.xingkelaochen.javademo.springcloud.feign.api.service.HelloService;

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
public class HelloServiceFallback implements HelloService {

	@Override
	public String hello(User user) {
		
		return "error";
	}

}
