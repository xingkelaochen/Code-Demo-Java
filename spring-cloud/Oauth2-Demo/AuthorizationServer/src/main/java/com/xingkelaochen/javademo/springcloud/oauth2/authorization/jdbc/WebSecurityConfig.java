package com.xingkelaochen.javademo.springcloud.oauth2.authorization.jdbc;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * 配置Security过滤机制
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
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		http.antMatcher("/**").authorizeRequests()
//			.antMatchers("/","/favicon.ico","/h2-console/**","/login/**").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.exceptionHandling()
//			.and()
//			.logout()
//			.logoutSuccessUrl("/").permitAll()
//			// 设置frameOptions为disable，防止输出头中添加X-Frame-Options=deny，导致页面不可被嵌入到frame中
//			.and()
//			.headers().frameOptions().disable()
//			.and()
//			// 默认csrf是开启的
//			.csrf().disable()
//			.httpBasic().disable();
		
		http
			.csrf().disable()
			.headers().frameOptions().disable();
		
		super.configure(http);

	}

}
