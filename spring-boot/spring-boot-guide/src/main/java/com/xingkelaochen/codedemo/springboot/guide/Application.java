package com.xingkelaochen.codedemo.springboot.guide;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot�Ƽ�ʹ��@Configuration�ķ�ʽ������ԭ��ʹ��xml�ļ��н����������õ�ģʽ��
 * �������ʹ��xml�ļ��������ã���ôSpring BootҲ�Ƽ�ʹ�����ַ�ʽ��������@ImportResource����xml�ļ���ָ����<br />
 * Spring Boot�ṩ��һ����ݵ����÷�ʽ��ʹ��@EnableAutoConfigurationע�⽫������Ŀ������Spring Boot�����������Զ�����Spring Boot����������(Լ����������)��
 * Ҳ����ʹ��excludeNameָ���࣬�Ӷ��رղ���Ҫ���Զ����á�<br />
 * ע��@ComponentScan����ָ��Spring���������ɨ��·��������ʹ��basePackagesָ�����������·����<br />
 * ʹ��@SpringBootApplicationע�⣬����ɨ���Ӱ��£�����ָ������������Spring�������������Ӧ���Զ����á����൱��ͬʱ������Configuration��EnableAutoConfiguration��ComponentScanע�⡣<br />
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
//@Configuration
//@EnableAutoConfiguration(exclude= {})
//@ComponentScan(basePackages= {})
@SpringBootApplication
public class Application {
	
	/**
	 * ������SpringBoot��ֹ��ʱ�򣬷���ExitCodeGeneratorʵ�������а�����exitCode��
	 * @return
	 */
	@Bean
	public ExitCodeGenerator exitCodeGenerator() {
		
		return ()->111;
	}
	
	/**
	 * ������SpringBoot��ֹ��ʱ�������˳���Ӧ�����٣�destroyʱ�����д��롣
	 * @return
	 */
	@Bean
	public DisposableBean disposableBean() {
		return ()->{};
	}
	
	/**
	 * ע��@PreDestroy���ϱߵ�������ͬ����Ч��
	 */
	@PreDestroy
	public void preDestroy() {
		
	}
	
	/**
	 * ʹ��SpringApplication.run�ķ�ʽ����Spring BootӦ��ʱ��ʹ����Ƕ��tomcatӦ�÷������������Զ����á������ֱ�����д˳�����ڣ��Ӷ�����һ��Ĭ�Ͽ���ʹ��8080�˿ڷ��ʵ�HTTP����
	 * @param args
	 */
	public static void main(String[] args) {
		
		// ������򵥵�������ʽ
		SpringApplication.run(Application.class, args);
		
		// ��Ϊ������ExitCodeGenerator����SpringBootӦ����ֹʱ�����Խ�ָ����exitCode���ݸ�JVMִ���˳�������
//		System.exit(SpringApplication.exit(SpringApplication.run(Application.class, args)));
		
		// �Զ����������ʽ
//		SpringApplication app = new SpringApplication(Application.class);
//		app.setBannerMode(Mode.OFF);
//		app.run(args);
		
		// ����ʹ�����ַ�ʽ����ָ����ApplicationContext�㼶
//		SpringApplication app = new SpringApplicationBuilder(Application.class).sources(Parent.class).child(Application.class).build();
//		app.run(args);
		
		// Spring Boot��һЩ�¼�����ApplicationContext������֮ǰ�����ģ���˲��ܽ�������ע��Ϊ@Bean������ʹ�����µķ�ʽ���ü�����
//		app.addListeners(listeners);
		
	}
	
}
