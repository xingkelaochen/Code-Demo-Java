package com.xingkelaochen.codedemo.springboot.guide;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 
 * ʵ��CommandLineRunner�ӿ��Զ�����������������SpringBoot����ʱָ������һЩ���룬 ����SpringApplication.run���ǰ���С�<br />
 * ����Ŀ��������ʵ���˶��CommandLineRunner�ӿڣ�����Ҫʹ��@Orderָ����֧��˳��
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
@Order(value=0)
public class CommandLineRunnerImpl implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}

}
