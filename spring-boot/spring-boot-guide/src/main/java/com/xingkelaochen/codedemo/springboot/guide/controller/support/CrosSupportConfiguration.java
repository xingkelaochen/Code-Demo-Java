package com.xingkelaochen.codedemo.springboot.guide.controller.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通过重写addCorsMappings方法，添加指定路径的CROS支持，这是全局设置的方法。在具体Controller的类或者方法上也可以使用@CrossOrigin注解进行标注配置。
 * 
 * <p>
 * 	CROS：跨域资源共享，用于替代不安全、不灵活的ifream、jsonp的跨域请求，需要浏览器与服务端同时支持。
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
@Configuration
@EnableWebMvc
public class CrosSupportConfiguration {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {

				/**
				 * 指定可以进行CROS的请求路径
				 */
				registry.addMapping("/api/**").allowedOrigins("http://domain2.com").maxAge(3000).allowedMethods("GET","POST");
			}
			
		};
	}
}
