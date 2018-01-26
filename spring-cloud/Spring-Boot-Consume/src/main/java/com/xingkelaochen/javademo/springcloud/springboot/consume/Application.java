package com.xingkelaochen.javademo.springcloud.springboot.consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Eureka服务的消费者应用
 * 
 * <p>
 * 	使用@EnableDiscoveryClient，通过eureka.client.service-url.defaultZone配置，将主要负责以下几点与Eureka Server的操作：
 * 	1. 负责向注册中心注册自己
 *  2. 向注册中心租约
 *  3. 应用正常关闭时，向注册中心取消租约
 *  4. 查询注册中心的服务列表
 *  （上述所有交互都是通过Rest方式进行的）
 * </p>
 *
 * <p>
 * 	CAP原理：C（一致性）、A（可用性）、P（可靠性）。
 *  Eureka与Zookeeper不同：Zookeeper属于保证CP，当心跳机制超出异常接受范围时，将会把此服务剔除；而Eureka将启动自我保护机制，尽可能保证服务注册信息。
 *  <b>针对Eureka保证AP的特性，需要增强对这类问题的容错，可以在服务消费端开启重试机制。</b>
 * </p>
 * 
 * <p>
 * 	Ribbon是客户端负载均衡器，包含许多与负载功能相关的配置，例如：客户端配置、均衡策略、检测策略、服务实例维护机制、实例过滤机制等等。
 * 	为了方便使用，Ribbon提供了自动化配置进行相关的默认设置，当Eureka与Ribbon整合使用时，自动化的配置与默认有了针对性的些许区别：DiscoveryEnabledNIWSServerList、NIWSDiscoveryPing……
 * </p>
 * 
 * <p>
 * 	Hystrix是熔断器：微服务中的服务单元相互依赖，互相通过服务中心注册或发现服务，通过远程调用的方式进行互相通讯。
 *  如果因为网络或者自身问题导致调用方迟迟不能获取请求的响应，这在高并发的情况下会导致调用方任务积压，此问题根据依赖关系向上传导导致无法控制的蔓延，最终可能造成整个服务的瘫痪。
 * </p>
 * 
 * <p>
 * 	请求合并：在高并发的应用场景下，一定时间内请求同一个服务接口过多的话，每个请求都消耗资源进行单独处理，这无疑给服务器造成太大的压力。
 *  使用请求合并的作用是，将一定时间内，对于同一个请求的请求打包进行一次请求，这也需要服务方提供相应的批量接口来实现。
 *  Hystrix可以在HystrixCommand之前进行请求合并的处理。
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

// 使用@EnableDiscoveryClient标注此应用为Eureka客户端，将获取注册服务获取功能。
@EnableDiscoveryClient

// 使用@RibbonClient进行Ribbon的定制化，或者直接申明配置Bean
// @RibbonClient(name="",configuration= {})

// 使用@EnableCircuitBreaker开启熔断器的支持
@EnableCircuitBreaker
public class Application {

	/**
	 * 使用@LoadBalanced开户客户端负载均衡功能
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
