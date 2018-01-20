package com.xingkelaochen.codedemo.springboot.guide.controller.custom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * �Զ����Spring Mvc���ã�ͨ��@Bean��ע��Spring���Զ��������ͽ����滻��
 * 
 * <p>
 * 	ͨ���̳�WebMvcConfigurationSupport�࣬��д������Ӧ�ķ��������Բ���Spring MvcԤ�õĸ��ֹ������ԡ�
 *  ע�⣺���ʹ��@Configuration��@EnableWebMvcע����б�ע��@EnableAutoConfiguration���Զ����û�ʧЧ������Ҫ�ֶ����и������ã�����μ�org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration 
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
@Configuration
public class CustomWebMvcConfiguration extends WebMvcConfigurationSupport {

	@Autowired
	private CustomConvertersConfiguration customConvertersConfiguration;
	
	@Override
	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		// TODO Auto-generated method stub
		return super.requestMappingHandlerAdapter();
	}

	@Override
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		// TODO Auto-generated method stub
		return super.requestMappingHandlerMapping();
	}
	

	@Override
	@Bean
	public HandlerExceptionResolver handlerExceptionResolver() {
		// TODO Auto-generated method stub
		return super.handlerExceptionResolver();
	}

	@Override
	public HandlerMapping defaultServletHandlerMapping() {
		// TODO Auto-generated method stub
		return super.defaultServletHandlerMapping();
	}

	/**
	 * ����ת�����б������������ôĬ�Ͻ���������8��ת������
	 *  org.springframework.http.converter.ByteArrayHttpMessageConverter
		org.springframework.http.converter.StringHttpMessageConverter
		org.springframework.http.converter.ResourceHttpMessageConverter
		org.springframework.http.converter.ResourceRegionHttpMessageConverter
		org.springframework.http.converter.xml.SourceHttpMessageConverter
		org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter
		org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter
		org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
	 * 
	 * 
	 */
	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
	}
	
	
	/**
	 * ��չת���������Զ����HttpMessageConverter��ӵ�ת�����б��У�ԭʼת�����б���configureMessageConvertersȷ��
	 * 
	 */
	@Override
	protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

		// ʹ��Ĭ�ϵ�ת���������json�ַ���Ϊ{"msg":"java.lang.NullPointerException","status":500}
		// ʹ���Զ����ת������� ExceptionResponseObject�������ת���������{"errorInfo":"java.lang.NullPointerException[500]"}
		converters.add(customConvertersConfiguration.customExceptionResponseObjectConverter());
		
	}

	/**
	 * ����ȫ����ͼ������������·���ķ�����Ӧӳ�䵽/public/index.html��̬�ļ���
	 */
	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {

		 registry.addViewController("/").setViewName("forward:/res/index.html");
		 super.addViewControllers(registry);
		
	}

	/**
	 * Ĭ�Ͼ�̬�ļ�Ĭ��·��Ϊ/static (or /public or /resources or /META-INF/resources)��ʹ������ͨ��DispatcherServlet������ֱ�ӽ���URL���ʡ�
	 * <p>
	 * �����÷�����ͬ����������spring.mvc.static-path-pattern��spring.resources.static-locations��
	 * �ڱ�ʾ���У�����ʹ�ü̳е�WebMvcConfigurationSupport������������ã������ļ��е���������ʧЧ�ˡ�
	 * ���������Ϊ�̳е�WebMvcConfigurationSupport��������ʹ��@EnableWebMvc��ע������һ����Ч���ɡ�
	 * 
	 * </p>
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/res/**").addResourceLocations("classpath:/public/");
		super.addResourceHandlers(registry);
	}

	
}
