package com.xingkelaochen.javademo.springcloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

/**
 * Spring Boot��Zipkin���ϣ�ΪSpring Cloud΢����ĵ�����·�ṩ���ٹ��ܡ�
 * ��Ҫ��΢����������spring-cloud-sleuth-zipkin����ʹ��spring.zipkin.base-url����zipkin���Ϸ���ķ��ʵ�ַ
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
@SpringBootApplication
@EnableZipkinServer
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
