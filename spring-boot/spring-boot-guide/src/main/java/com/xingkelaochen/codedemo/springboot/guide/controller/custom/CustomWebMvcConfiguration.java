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
 * 自定义的Spring Mvc配置，通过@Bean标注，Spring将自动按照类型进行替换。
 * 
 * <p>
 * 	通过继承WebMvcConfigurationSupport类，重写其中相应的方法，可以操作Spring Mvc预置的各种功能特性。
 *  注意：如果使用@Configuration与@EnableWebMvc注解进行标注，@EnableAutoConfiguration的自动配置会失效，则需要手动进行各项配置，具体参见org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration 
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

	/**
	 * 配置全局视图控制器，将根路径的访问响应映射到/public/index.html静态文件。
	 */
	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {

		 registry.addViewController("/").setViewName("forward:/res/index.html");
		 super.addViewControllers(registry);
		
	}

	/**
	 * 默认静态文件默认路径为/static (or /public or /resources or /META-INF/resources)，使其请求不通过DispatcherServlet，可以直接进行URL访问。
	 * <p>
	 * 此设置方法等同于属性设置spring.mvc.static-path-pattern与spring.resources.static-locations。
	 * 在本示例中，由于使用继承的WebMvcConfigurationSupport来进行相关配置，属性文件中的上述配置失效了。
	 * 这可能是因为继承的WebMvcConfigurationSupport的配置与使用@EnableWebMvc标注产生了一样的效果吧。
	 * 
	 * </p>
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/res/**").addResourceLocations("classpath:/public/");
		super.addResourceHandlers(registry);
	}

	
}
