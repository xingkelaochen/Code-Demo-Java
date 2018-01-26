package com.xingkelaochen.javademo.springcloud.springboot.consume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.xingkelaochen.javademo.springcloud.springboot.consume.service.HelloServiceConsume;
import com.xingkelaochen.javademo.springcloud.springboot.consume.vo.User;

/**
 * ��ʾ����Eureka��������������߲���
 * 
 * <p>
 * 	ʹ��Ribbon�ͻ��˸��ء�Hystrix�۶���
 * </p>
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
@RestController
@RequestMapping(value="/helloServiceConsume")
public class HelloServiceConsumeController {

	@Autowired
	private HelloServiceConsume helloServiceConsume;
	
	@RequestMapping("hello")
	public String hello() {
		
		return helloServiceConsume.hello();
		
	}
	
	@RequestMapping(value="getUserById/{id}",method= {RequestMethod.GET})
	public User getUserById(@PathVariable String id) {
		
		// �����ʼ��HystrixRequestContext�����������ʼ���Ǵ���ģ���Ϊÿ�ζ��������µ������ģ����»��沢û����ȷ��ʹ�ã��˴�ֻ��������ʾ��
		HystrixRequestContext.initializeContext();
		
		return helloServiceConsume.getUserById(id);
		
	}
	
}
