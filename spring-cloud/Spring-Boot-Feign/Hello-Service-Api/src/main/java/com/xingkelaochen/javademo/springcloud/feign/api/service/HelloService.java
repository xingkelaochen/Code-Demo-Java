package com.xingkelaochen.javademo.springcloud.feign.api.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xingkelaochen.javademo.springcloud.feign.api.dto.User;

/**
 * 服务接口定义
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
@RequestMapping("/helloService")
public interface HelloService {

	@RequestMapping("hello")
	public String hello(@RequestBody User user);
	
}
