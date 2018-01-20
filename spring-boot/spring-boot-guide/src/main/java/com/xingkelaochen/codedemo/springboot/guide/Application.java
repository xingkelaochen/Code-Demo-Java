package com.xingkelaochen.codedemo.springboot.guide;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot推荐使用@Configuration的方式，代替原本使用xml文件中进行属性配置的模式。
 * 如果必须使用xml文件进行配置，那么Spring Boot也推荐使用这种方式，可以用@ImportResource进行xml文件的指定。<br />
 * Spring Boot提供了一个便捷的配置方式，使用@EnableAutoConfiguration注解将根据项目所配置Spring Boot的起步依赖，自动进行Spring Boot容器的配置(约定大于配置)。
 * 也可以使用excludeName指定类，从而关闭不需要的自动配置。<br />
 * 注解@ComponentScan用于指定Spring加载组件的扫描路径，可以使用basePackages指定多个根包的路径。<br />
 * 使用@SpringBootApplication注解，将会扫描子包下（或者指定包）的所有Spring组件，并进行相应的自动配置。这相当于同时声明了Configuration、EnableAutoConfiguration、ComponentScan注解。<br />
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
	 * 申明当SpringBoot终止的时候，返回ExitCodeGenerator实例，其中包含了exitCode。
	 * @return
	 */
	@Bean
	public ExitCodeGenerator exitCodeGenerator() {
		
		return ()->111;
	}
	
	/**
	 * 申明当SpringBoot终止的时候，容器退出、应用销毁，destroy时的运行代码。
	 * @return
	 */
	@Bean
	public DisposableBean disposableBean() {
		return ()->{};
	}
	
	/**
	 * 注解@PreDestroy与上边的声明有同样的效果
	 */
	@PreDestroy
	public void preDestroy() {
		
	}
	
	/**
	 * 使用SpringApplication.run的方式启动Spring Boot应用时将使用内嵌的tomcat应用服务器并进行自动配置。你可以直接运行此程序入口，从而启动一个默认可以使用8080端口访问的HTTP服务。
	 * @param args
	 */
	public static void main(String[] args) {
		
		// 这是最简单的启动方式
		SpringApplication.run(Application.class, args);
		
		// 因为声明了ExitCodeGenerator，当SpringBoot应用终止时，可以将指定的exitCode传递给JVM执行退出操作。
//		System.exit(SpringApplication.exit(SpringApplication.run(Application.class, args)));
		
		// 自定义的启动方式
//		SpringApplication app = new SpringApplication(Application.class);
//		app.setBannerMode(Mode.OFF);
//		app.run(args);
		
		// 可以使用这种方式创建指定的ApplicationContext层级
//		SpringApplication app = new SpringApplicationBuilder(Application.class).sources(Parent.class).child(Application.class).build();
//		app.run(args);
		
		// Spring Boot的一些事件是在ApplicationContext被创建之前触发的，因此不能将监听器注册为@Bean，可以使用以下的方式配置监听器
//		app.addListeners(listeners);
		
	}
	
}
