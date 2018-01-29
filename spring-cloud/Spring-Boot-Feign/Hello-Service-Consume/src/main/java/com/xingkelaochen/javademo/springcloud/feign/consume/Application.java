package com.xingkelaochen.javademo.springcloud.feign.consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.Logger;

/**
 * ��Spring Cloud�ķ������Ѷ���ʹ��Ribbon��Hystrix�����ؾ������·����
 * ���Ϸ����ǣ�ʹ��@LoadBalanced��עRestTemplate�������ع��ܣ�ʹ��@EnableCircuitBreaker֧�ֶ�·�����ܣ����ڷ����ϱ�ע@HystrixCommand������·����
 * ��ʾ����ʾʹ��@Feign��������������ֹ��ܵķ���������ʹ��@EnableFeignClients������Feign��֧�֡�
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
@EnableDiscoveryClient
@EnableFeignClients
public class Application {

	/**
	 * FeignΪÿ���ͻ��˶��Զ�����һ����־��Ĭ�ϼ���ΪINFO������ͨ�������ķ�ʽ�������á���Ҳ������@FeignClient��ʹ��confinuration���е����������ָ������
	 * @return
	 */
	@Bean
	public Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
