package com.xingkelaochen.codedemo.springboot.guide.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.xingkelaochen.codedemo.springboot.guide.data.custom.MyBaseRepositoryImpl;

/**
 * 
 * Spring Boot应用的容器配置类，使用@Profile注解指定在哪种环境如下加载配置
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
@Profile(value="dev")
// 指定自定义通用的Repository，详见MyBaseRepository
@EnableJpaRepositories(basePackages= {"com.xingkelaochen.codedemo.springboot.guide.data"},repositoryBaseClass = MyBaseRepositoryImpl.class)
public class ApplicationConfinguration {

	/**
	 * SpringContext中声明一个HelloWorld对象，并可用此方法获取这个对象
	 * @return
	 */
	@Bean
	public HelloWorld getHelloWorld() {
		return new HelloWorld();
	}
	
}

class HelloWorld{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
