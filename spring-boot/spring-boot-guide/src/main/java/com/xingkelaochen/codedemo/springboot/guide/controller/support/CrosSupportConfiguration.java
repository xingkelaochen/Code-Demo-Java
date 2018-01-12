package com.xingkelaochen.codedemo.springboot.guide.controller.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ͨ����дaddCorsMappings���������ָ��·����CROS֧�֣�����ȫ�����õķ������ھ���Controller������߷�����Ҳ����ʹ��@CrossOriginע����б�ע���á�
 * 
 * <p>
 * 	CROS��������Դ���������������ȫ��������ifream��jsonp�Ŀ���������Ҫ�����������ͬʱ֧�֡�
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
				 * ָ�����Խ���CROS������·��
				 */
				registry.addMapping("/api/**").allowedOrigins("http://domain2.com").maxAge(3000).allowedMethods("GET","POST");
			}
			
		};
	}
}
