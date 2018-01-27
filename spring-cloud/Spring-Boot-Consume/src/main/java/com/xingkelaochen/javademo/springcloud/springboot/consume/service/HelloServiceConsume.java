package com.xingkelaochen.javademo.springcloud.springboot.consume.service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.assertj.core.condition.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.xingkelaochen.javademo.springcloud.springboot.consume.vo.User;

import rx.Observable;
import rx.Subscriber;

@Service
public class HelloServiceConsume {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 使用RestTemplate负载均衡，通过服务注册中心请求服务列表，使用Ribbon客户端负载策略，请求服务提供者接口并返回结果
	 * 
	 * <p>
	 * 	使用Hystrix熔断器，当服务接口调用异常时，将使用helloFallback方法进行服务降级处理，这将使用指定的方法处理逻辑；但当抛出的异常符合ignoreExceptions指定类型时，将不使用helloFallback指定方法，而是由HystrixBadRequestExceptrion抛出。<tr />
	 *  注意：要使熔断器有效，则必须保证Eureka服务中心的自我保护模式开启（保证无法维持正常心跳的服务提供方信息依然存在），并且客户端使用@EnableCircuitBreaker标注开启Hystrix功能。
	 *  因为Ribbon客户端负载均衡默认使用轮循的方式进行负载，所以如果服务提供者有多个节点，那么将在请求不可访问节点时使错误的回调设置生效。
	 * </p>
	 * 
	 * @return
	 */
	@HystrixCommand(fallbackMethod="helloFallback",ignoreExceptions={Exception.class})
	public String hello() {
		
		// Http url并不是一个真实的地址，而是Eureka中注册的Spring Boot应用名称 
		// 由DynamicServerListLoadBalancer进行负载均衡决策请求服务
		// postForEntity、put、delete、
		return restTemplate.getForEntity("http://SPRING-BOOT-SERVICE/hello",String.class).getBody();
		
	}
	
	/**
	 * 异步方法调用，返回Future对象。
	 * 或者继承HystrixCommand对象，重写其中的run方法。
	 * @return
	 */
	@HystrixCommand
	public Future<String> helloAsync() {
		
		return new AsyncResult<String>() {

			@Override
			public String invoke() {
				return restTemplate.getForEntity("http://SPRING-BOOT-SERVICE/hello",String.class).getBody();
			}
			
		};
		
	}
	
	/**
	 * 响应式方法，返回Observable对象。
	 * 或者继承HystrixObservableCommand对象。
	 * <p>
	 * 	在使用@HystrixCommand的响应式命令时，可以通过observableExecutionMode设置observe()、toObservable()的执行方式。
	 *  其中设置为EAGER为使用observe()方式，设置为LAZY为使用toObservable()方式。
	 * </p>
	 * @return
	 */
	@HystrixCommand(observableExecutionMode=ObservableExecutionMode.EAGER)
	public Observable<String> helloObs(){
		return Observable.create(new Observable.OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> arg0) {
				try {
					if(arg0.isUnsubscribed()) {
						String str = restTemplate.getForEntity("http://SPRING-BOOT-SERVICE/hello",String.class).getBody();
						arg0.onNext(str);
						arg0.onCompleted();
					}
				}catch(Exception ex) {
					arg0.onError(ex);
				}
			}
			
			
		});
	}
	
	/**
	 * Hystrix请求缓存处理。使用@CacheResult开启请求缓存支持，可以使用@CacheKey指定具体的缓存KEY（使用@CacheKey会导致异常：Method not found: isId，原因未知）。
	 * 若要清除缓存则使用@CacheRemove(commandKey="getUserById")标注指定方法，其中commandKey属性必须指定。
	 * 
	 * @param id
	 * @return
	 */
	@HystrixCommand
	@CacheResult
	public User getUserById(String id) {
		
		return restTemplate.getForEntity("http://SPRING-BOOT-SERVICE/user/{1}",User.class,id).getBody();
		
	}
	
	/**
	 * 支持Hystrix的请求合并功能，使用@HystrixCollaps标注，在指定时间窗口范围内的请求将使用批量方法进行打包请求。
	 * 也可以通过继承HystrixCollapser实现
	 * @param id
	 * @return
	 */
	@HystrixCollapser(batchMethod="getUserListByIds",collapserProperties= {@HystrixProperty(name="timerDelayInMilliseconds",value="100")})
	public User getUserByIdOptimization(String id) {
		
		return null;
		
	}
	
	@HystrixCommand
	public List<User> getUserListByIds(List<String> ids){
		
		String str = ids.stream().collect(Collectors.joining(","));
		
		return restTemplate.getForEntity("http://SPRING-BOOT-SERVICE/users/{1}",List.class,str).getBody();
	}
	
	
	/**
	 * 使用@HystrixCommand标注的fallbackMethod服务降级处理方法，需要申明在本类中，可以使用参数获取异常对象。
	 * @param throwable
	 * @return
	 */
	public String helloFallback(Throwable throwable) {
		
		return "调用服务接口异常！";
		
	}
	
}
