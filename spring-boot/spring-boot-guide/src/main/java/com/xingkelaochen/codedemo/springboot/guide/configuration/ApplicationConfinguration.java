package com.xingkelaochen.codedemo.springboot.guide.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 
 * Spring BootӦ�õ����������࣬ʹ��@Profileע��ָ�������ֻ������¼�������
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
public class ApplicationConfinguration {

	/**
	 * SpringContext������һ��HelloWorld���󣬲����ô˷�����ȡ�������
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
