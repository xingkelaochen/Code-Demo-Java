package com.xingkelaochen.javademo.springcloud.springboot.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Eureka������ṩ��Ӧ��
 * 
 * <p>
 * 	ʹ��@EnableDiscoveryClient��ͨ��eureka.client.service-url.defaultZone���ã�����Ҫ�������¼�����Eureka Server�Ĳ�����
 * 	1. ������ע������ע���Լ�
 *  2. ��ע��������Լ
 *  3. Ӧ�������ر�ʱ����ע������ȡ����Լ
 *  4. ��ѯע�����ĵķ����б�
 *  ���������н�������ͨ��Rest��ʽ���еģ�
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
// ʹ��@EnableEurekaClient��ע��Ӧ��ΪEureka�ͻ���Ӧ�ã����ṩ����
@EnableEurekaClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
