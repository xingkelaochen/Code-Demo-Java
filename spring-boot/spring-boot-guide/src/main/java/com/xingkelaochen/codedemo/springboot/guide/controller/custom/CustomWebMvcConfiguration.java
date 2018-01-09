package com.xingkelaochen.codedemo.springboot.guide.controller.custom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 自定义的Spring Mvc配置，通过@Bean标注，Spring将自动按照类型进行替换。
 * 
 * <p>
 * 	通过继承WebMvcConfigurationSupport类，重写其中相应的方法，可以操作Spring Mvc预置的各种功能特性
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
// 进行Spring MVC的配置，必须标注@EnableWebMvc注解
@EnableWebMvc
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

	/**
	 * 设置转换器列表，如果不设置那么默认将配置以下8个转换器：
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
	 * 扩展转换器，将自定义的HttpMessageConverter添加到转换器列表中，原始转换器列表由configureMessageConverters确定
	 * 
	 */
	@Override
	protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

		// 使用默认的转换器输出的json字符串为{"msg":"java.lang.NullPointerException","status":500}
		// 使用自定义的转换器针对 ExceptionResponseObject对象进行转换，将输出{"errorInfo":"java.lang.NullPointerException[500]"}
		converters.add(customConvertersConfiguration.customExceptionResponseObjectConverter());
		
	}

}
