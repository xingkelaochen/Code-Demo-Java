package com.xingkelaochen.javademo.springcloud.oauth2.authorization.jdbc;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	/**
	 * 模拟成功登录用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		
		UserDetails user = jdbcUserDetailsManager.loadUserByUsername("xingkelaochen");
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
		
		Authentication authentication = authenticationManager.authenticate(token);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		HttpSession session = request.getSession();  
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆  
        
        return "logined";
	}
	
}
