package com.xingkelaochen.codedemo.springcloud.euraka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka����ע������Ӧ��
 *
 * <p>
 * 	�ͻ���ͨ��REST��ʽ����ע�����Ľ��з���ע�ᡢ����ǩԼ�����٣��������ȡ�������Ȳ�����
 * </p>
 * <p>
 * 	����ע�ᵽע�����Ľ�ά��һ��˫��Map���ݽṹ����һ��Map��KeyΪ�������ƣ��ڶ���Map��KeyΪ����ʵ�����ƣ�InstanceInfo�е�instanceIdֵ��
 * </p>
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
// ʹ��@EnableEurekaServer��ע����Ӧ��Eureka����ע�����ĵ�֧��
@EnableEurekaServer
public class Application {
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
		
	}
	
}
