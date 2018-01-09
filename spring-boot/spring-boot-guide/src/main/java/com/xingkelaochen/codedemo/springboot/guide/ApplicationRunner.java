package com.xingkelaochen.codedemo.springboot.guide;

import org.springframework.boot.ApplicationArguments;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * ��ʵ��CommandLineRunner�ӿ�һ������������SpringBoot����ʱ����ָ���Ĵ���<br />
 * 
 * @see CommandLineRunnerImpl
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
@Component
@Order(value=1)
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub

	}

}
