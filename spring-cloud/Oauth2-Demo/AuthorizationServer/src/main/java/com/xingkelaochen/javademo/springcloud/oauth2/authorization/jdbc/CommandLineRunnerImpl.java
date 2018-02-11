package com.xingkelaochen.javademo.springcloud.oauth2.authorization.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;


@Component
@Profile("jdbc")
public class CommandLineRunnerImpl implements CommandLineRunner {

	private static Log log = LogFactory.getLog(CommandLineRunnerImpl.class);
	
	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;
	
	@Override
	public void run(String... args) throws Exception {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("test"));
		
		UserDetails user = new User("xingkelaochen", "123456", authorities);
		jdbcUserDetailsManager.createUser(user);
		
		log.debug("新建测试用户");
	}

}
