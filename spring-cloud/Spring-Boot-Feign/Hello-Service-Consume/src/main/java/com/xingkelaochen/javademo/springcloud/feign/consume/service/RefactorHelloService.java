package com.xingkelaochen.javademo.springcloud.feign.consume.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xingkelaochen.javademo.springcloud.feign.api.dto.User;

/**
 * 
 * ʹ��@FeignClient(value="Hello-Service")ָ����Eureka��ע��ķ��񣬿�����HelloService�ӿڵ�֧�֡�
 * ʹ��Feign�������Ѷ˵���������������Ҫʹ��MVC��@RequestMapping���а󶨣�����Ϊ�˽ӿڼ̳���HelloService�ӿڣ����Դ˽ӿڲ��������κη�����
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
