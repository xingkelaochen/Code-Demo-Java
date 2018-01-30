package com.xingkelaochen.javademo.springcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * һ�㣬Spring Cloud���������µĸ�΢�������������������ڲ���̬�����л���Eureka�����ע���뷢�ֻ�������ͨ�š�
 * ��ͳ�ķֲ�ʽӦ�ýṹ���ṩ���ⲿ�ķ�����ͨ������F5��nginx���ֶν��д���ת���͸��ؾ��⣬���ղŵ����ⲿ����ӿڡ�
 * Spring Cloudʹ����Zuul�����󵽴��ⲿ�ӿ�֮ǰ������һ��API���صĽṹ��ΪSpring Cloud�ṩһ����������棬�����Խ�������������⣺
 * <p>
 * 	1. ·��ת������������Eureka�����ڵ����ƣ�Zuul���Խ����Ϲ��������ת����ע�����ĵ�ָ�����񣨵�ȻҲ����ֱ��ָ��url����
 *  2. ���ˣ�Ϊ�ⲿ�������һ���Զ���Ĺ��˻��ƣ��������Ա���һЩͨ�õĹ��������ڶ��΢�����С�
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
	 * Ĭ������£�zuul�Զ�Ϊ���з��񴴽�·��ӳ�䣬��Ҳ����ͨ���˷�ʽ����ͳһĬ�ϵ��Զ���·�ɹ���
	 * ���磺����������Ƶ�ȫ������Ϊname-version������-�汾�ţ��������ʹ�ô˷���ӳ��·��Ϊ/version/name
	 * ����ʾ�龰��δʹ��������������������
	 * @return
	 */
//	@Bean
//	public PatternServiceRouteMapper serviceRouteMapper() {
//		return new PatternServiceRouteMapper("(?<name>^.+)-(?<vesion>.+$)", "${version}/${name}");
//	}
	
	public static void main(String[] args) {
		
		// ����Ϊweb���͵ķ����ṩ����servlet�����ط���
//		new SpringApplicationBuilder(Application.class).web(true).run(args);
		SpringApplication.run(Application.class, args);
	}
	
}
