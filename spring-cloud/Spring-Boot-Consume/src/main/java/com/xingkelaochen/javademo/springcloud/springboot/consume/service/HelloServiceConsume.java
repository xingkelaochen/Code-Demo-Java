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
	 * ʹ��RestTemplate���ؾ��⣬ͨ������ע��������������б�ʹ��Ribbon�ͻ��˸��ز��ԣ���������ṩ�߽ӿڲ����ؽ��
	 * 
	 * <p>
	 * 	ʹ��Hystrix�۶�����������ӿڵ����쳣ʱ����ʹ��helloFallback�������з��񽵼������⽫ʹ��ָ���ķ��������߼��������׳����쳣����ignoreExceptionsָ������ʱ������ʹ��helloFallbackָ��������������HystrixBadRequestExceptrion�׳���<tr />
	 *  ע�⣺Ҫʹ�۶�����Ч������뱣֤Eureka�������ĵ����ұ���ģʽ��������֤�޷�ά�����������ķ����ṩ����Ϣ��Ȼ���ڣ������ҿͻ���ʹ��@EnableCircuitBreaker��ע����Hystrix���ܡ�
	 *  ��ΪRibbon�ͻ��˸��ؾ���Ĭ��ʹ����ѭ�ķ�ʽ���и��أ�������������ṩ���ж���ڵ㣬��ô�������󲻿ɷ��ʽڵ�ʱʹ����Ļص�������Ч��
	 * </p>
	 * 
	 * @return
	 */
	@HystrixCommand(fallbackMethod="helloFallback",ignoreExceptions={Exception.class})
	public String hello() {
		
		// Http url������һ����ʵ�ĵ�ַ������Eureka��ע���Spring BootӦ������ 
		// ��DynamicServerListLoadBalancer���и��ؾ�������������
		// postForEntity��put��delete��
		return restTemplate.getForEntity("http://SPRING-BOOT-SERVICE/hello",String.class).getBody();
		
	}
	
	/**
	 * �첽�������ã�����Future����
	 * ���߼̳�HystrixCommand������д���е�run������
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
	 * ��Ӧʽ����������Observable����
	 * ���߼̳�HystrixObservableCommand����
	 * <p>
	 * 	��ʹ��@HystrixCommand����Ӧʽ����ʱ������ͨ��observableExecutionMode����observe()��toObservable()��ִ�з�ʽ��
	 *  ��������ΪEAGERΪʹ��observe()��ʽ������ΪLAZYΪʹ��toObservable()��ʽ��
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
	 * Hystrix���󻺴洦��ʹ��@CacheResult�������󻺴�֧�֣�����ʹ��@CacheKeyָ������Ļ���KEY��ʹ��@CacheKey�ᵼ���쳣��Method not found: isId��ԭ��δ֪����
	 * ��Ҫ���������ʹ��@CacheRemove(commandKey="getUserById")��עָ������������commandKey���Ա���ָ����
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
	 * ֧��Hystrix������ϲ����ܣ�ʹ��@HystrixCollaps��ע����ָ��ʱ�䴰�ڷ�Χ�ڵ�����ʹ�������������д������
	 * Ҳ����ͨ���̳�HystrixCollapserʵ��
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
	 * ʹ��@HystrixCommand��ע��fallbackMethod���񽵼�����������Ҫ�����ڱ����У�����ʹ�ò�����ȡ�쳣����
	 * @param throwable
	 * @return
	 */
	public String helloFallback(Throwable throwable) {
		
		return "���÷���ӿ��쳣��";
		
	}
	
}
