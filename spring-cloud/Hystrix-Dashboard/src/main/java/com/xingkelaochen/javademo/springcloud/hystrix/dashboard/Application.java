package com.xingkelaochen.javademo.springcloud.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Hystrix���Ǳ���������������ָ����Ϣ��
 * ����Hystrix�ķ������Ѷ�ʹ��@EnableCircuitBreaker������·�����ܣ�ʹ��actuator�ṩһ��/hystrix.stream�˿ڹ�hystrix-dashboard����չʾ��
 * �ڱ�Ӧ���������/hystrixҳ�������Ӧ/hystrix.stream��ַ�Ϳ��Բ鿴ͼ�λ���չʾ���档
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
@EnableHystrixDashboard
public class Application {

	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
}
