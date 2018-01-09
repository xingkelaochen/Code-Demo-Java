package com.xingkelaochen.codedemo.springboot.guide;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 
 * 实现CommandLineRunner接口自定义启动方法，以在SpringBoot启动时指定运行一些代码， 将在SpringApplication.run完成前运行。<br />
 * 若项目中声明并实现了多个CommandLineRunner接口，则需要使用@Order指定其支行顺序
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
