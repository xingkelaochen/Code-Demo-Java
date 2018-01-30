package com.xingkelaochen.javademo.springcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * 一般，Spring Cloud服务治理下的各微服务或者组件，都在其内部生态环境中基于Eureka服务的注册与发现基础进行通信。
 * 传统的分布式应用结构，提供给外部的服务都是通过类似F5、nginx等手段进行代理转发和负载均衡，最终才到达外部服务接口。
 * Spring Cloud使用了Zuul在请求到达外部接口之前增加了一层API网关的结构，为Spring Cloud提供一个对外的门面，它可以解决以下两种问题：
 * <p>
 * 	1. 路由转发：依托与在Eureka体制内的优势，Zuul可以将符合规则的请求转发给注册中心的指定服务（当然也可以直接指定url）。
 *  2. 过滤：为外部请求添加一个自定义的过滤机制，这样可以避免一些通用的功能冗余在多个微服务中。
 * </p>
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
@SpringCloudApplication
@EnableZuulProxy
public class Application {

	/**
	 * 默认情况下，zuul自动为所有服务创建路由映射，但也可以通过此方式进行统一默认的自定义路由规则。
	 * 比如：如果服务名称的全名规则为name-version（名称-版本号），则可以使用此方法映射路由为/version/name
	 * 本演示情景并未使用如上描述的命名规则
	 * @return
	 */
//	@Bean
//	public PatternServiceRouteMapper serviceRouteMapper() {
//		return new PatternServiceRouteMapper("(?<name>^.+)-(?<vesion>.+$)", "${version}/${name}");
//	}
	
	public static void main(String[] args) {
		
		// 申明为web类型的服务，提供基于servlet的网关服务
//		new SpringApplicationBuilder(Application.class).web(true).run(args);
		SpringApplication.run(Application.class, args);
	}
	
}
