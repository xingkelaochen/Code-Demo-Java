package com.xingkelaochen.javademo.springcloud.feign.consume.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.xingkelaochen.javademo.springcloud.feign.api.service.HelloService;

/**
 * 
 * ʹ��@FeignClient(value="Hello-Service")ָ����Eureka��ע��ķ��񣬿�����HelloService�ӿڵ�֧�֡�
 * ʹ��Feign�������Ѷ˵���������������Ҫʹ��MVC��@RequestMapping���а󶨣�����Ϊ�˽ӿڼ̳���HelloService�ӿڣ����Դ˽ӿڲ��������κη�����
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
@FeignClient(value="HELLO-SERVICE",fallback=HelloServiceFallback.class)
public interface RefactorHelloService extends HelloService {

}
