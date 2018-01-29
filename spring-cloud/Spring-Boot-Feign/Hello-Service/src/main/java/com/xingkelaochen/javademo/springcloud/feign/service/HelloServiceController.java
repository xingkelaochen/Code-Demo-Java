package com.xingkelaochen.javademo.springcloud.feign.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xingkelaochen.javademo.springcloud.feign.api.dto.User;
import com.xingkelaochen.javademo.springcloud.feign.api.service.HelloService;

/**
 * 依赖Hello-Service-Api，继承了HelloService接口，接口已经申明了@RequestMapping属性，此处只需要标注@RestController即可。
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
