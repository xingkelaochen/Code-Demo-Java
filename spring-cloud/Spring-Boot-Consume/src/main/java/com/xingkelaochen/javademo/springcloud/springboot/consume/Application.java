package com.xingkelaochen.javademo.springcloud.springboot.consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Eureka�����������Ӧ��
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
 * <p>
 * 	CAPԭ��C��һ���ԣ���A�������ԣ���P���ɿ��ԣ���
 *  Eureka��Zookeeper��ͬ��Zookeeper���ڱ�֤CP�����������Ƴ����쳣���ܷ�Χʱ������Ѵ˷����޳�����Eureka���������ұ������ƣ������ܱ�֤����ע����Ϣ��
 *  <b>���Eureka��֤AP�����ԣ���Ҫ��ǿ������������ݴ������ڷ������Ѷ˿������Ի��ơ�</b>
 * </p>
 * 
 * <p>
 * 	Ribbon�ǿͻ��˸��ؾ���������������븺�ع�����ص����ã����磺�ͻ������á�������ԡ������ԡ�����ʵ��ά�����ơ�ʵ�����˻��Ƶȵȡ�
 * 	Ϊ�˷���ʹ�ã�Ribbon�ṩ���Զ������ý�����ص�Ĭ�����ã���Eureka��Ribbon����ʹ��ʱ���Զ�����������Ĭ����������Ե�Щ������DiscoveryEnabledNIWSServerList��NIWSDiscoveryPing����
 * </p>
 * 
 * <p>
 * 	Hystrix���۶�����΢�����еķ���Ԫ�໥����������ͨ����������ע����ַ���ͨ��Զ�̵��õķ�ʽ���л���ͨѶ��
 *  �����Ϊ��������������⵼�µ��÷��ٳٲ��ܻ�ȡ�������Ӧ�����ڸ߲���������»ᵼ�µ��÷������ѹ�����������������ϵ���ϴ��������޷����Ƶ����ӣ����տ���������������̱����
 * </p>
 * 
 * <p>
 * 	����ϲ����ڸ߲�����Ӧ�ó����£�һ��ʱ��������ͬһ������ӿڹ���Ļ���ÿ������������Դ���е������������ɸ����������̫���ѹ����
 *  ʹ������ϲ��������ǣ���һ��ʱ���ڣ�����ͬһ�����������������һ��������Ҳ��Ҫ�����ṩ��Ӧ�������ӿ���ʵ�֡�
 *  Hystrix������HystrixCommand֮ǰ��������ϲ��Ĵ���
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

// ʹ��@EnableDiscoveryClient��ע��Ӧ��ΪEureka�ͻ��ˣ�����ȡע������ȡ���ܡ�
@EnableDiscoveryClient

// ʹ��@RibbonClient����Ribbon�Ķ��ƻ�������ֱ����������Bean
// @RibbonClient(name="",configuration= {})

// ʹ��@EnableCircuitBreaker�����۶�����֧��
@EnableCircuitBreaker
public class Application {

	/**
	 * ʹ��@LoadBalanced�����ͻ��˸��ؾ��⹦��
	 * 
	 * @return
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
