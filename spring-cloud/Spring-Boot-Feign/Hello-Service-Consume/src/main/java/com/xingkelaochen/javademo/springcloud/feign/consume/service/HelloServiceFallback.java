package com.xingkelaochen.javademo.springcloud.feign.consume.service;

import com.xingkelaochen.javademo.springcloud.feign.api.dto.User;
import com.xingkelaochen.javademo.springcloud.feign.api.service.HelloService;

/**
 * 出现异常服务降级处理方法。在使用@HystrixCommand注解的方式中，使用fallbackMethod属性指定同类中的方法名称；此种实现方式需要在@Feign注解中使用fallback指定此类
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
