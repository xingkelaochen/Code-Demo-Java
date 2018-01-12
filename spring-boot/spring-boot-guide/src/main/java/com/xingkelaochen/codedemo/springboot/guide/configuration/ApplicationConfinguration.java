package com.xingkelaochen.codedemo.springboot.guide.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.xingkelaochen.codedemo.springboot.guide.data.custom.MyBaseRepositoryFactoryBean;

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
// ָ���Զ���ʵ�ֵ�RepositoryFactoryBean�����MyBaseRepositoryFactoryBean
@EnableJpaRepositories(repositoryFactoryBeanClass=MyBaseRepositoryFactoryBean.class)
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
