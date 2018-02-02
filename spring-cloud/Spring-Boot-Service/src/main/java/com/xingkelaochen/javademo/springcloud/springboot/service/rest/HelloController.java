package com.xingkelaochen.javademo.springcloud.springboot.service.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xingkelaochen.javademo.springcloud.springboot.service.data.User;

@RestController
@RequestMapping(value="/")
public class HelloController {

	private static Log log = LogFactory.getLog(HelloController.class);
	
	@RequestMapping(value="hello")
	public String hello(HttpServletRequest request) {
		
		// 因为开启了sleuth，所以在request的head中存在一些上级服务传递过来的跟踪信息
		String traceId = request.getHeader("X-B3-TraceId");
		
		return "hello world!["+traceId+"]";
		
	}
	
	@RequestMapping(value="user/{id}",method= {RequestMethod.GET})
	public User user(@PathVariable String id) {
			
		User user = new User(id,"xingkelaochen","男",33);
		return user;
	}
	
	
	@RequestMapping(value="users/{ids}",method= {RequestMethod.GET})
	public List<User> users(@PathVariable String ids){
		
		String[] idArray = ids.split(",");
		
		List<User> users = new ArrayList<>();
		
		for(String id:idArray) {
			if(!id.isEmpty()) {
				
				User user = new User(id,"xingkelaochen","男",33);
				
				users.add(user);
				
			}
		}
		
		return users;
	}
	
}
