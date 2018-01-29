package com.xingkelaochen.javademo.springcloud.feign.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xingkelaochen.javademo.springcloud.feign.api.dto.User;
import com.xingkelaochen.javademo.springcloud.feign.api.service.HelloService;

/**
 * ����Hello-Service-Api���̳���HelloService�ӿڣ��ӿ��Ѿ�������@RequestMapping���ԣ��˴�ֻ��Ҫ��ע@RestController���ɡ�
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
public class HelloServiceController implements HelloService {

	@Override
	public String hello(@RequestBody User user) {
		
		return "hello "+user.getName();
		
	}

}
