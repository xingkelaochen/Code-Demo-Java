package com.xingkelaochen.codedemo.springboot.guide.controller.custom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xingkelaochen.codedemo.springboot.guide.controller.error.ExceptionResponseObject;

@Configuration
public class CustomConvertersConfiguration {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * SpringBoot 默认使用HttpMessageConverter进行http请求与响应的消息转换，例如对象能被自动的换为json格式的字符串，字符串默认编码格式为UTF-8。
	 * <p>
	 * 	申明包含HttpMessageConverter的HttpMessageConverters对象，将一些自定义的转换器添加到SpringBoot转换器链中，这些转换器将根据预定规则对输入/输出内容进行转换。
	 * </p>
	 * 使用@Bean标注此方法将使Spring实例化此对象，并可使用此方法获取这个对象。
	 * @return
	 */
	@Bean
	public HttpMessageConverter<ExceptionResponseObject> customExceptionResponseObjectConverter() {
		
		/**
		 * ExceptionResponseObject类型不使用默认的json格式化，自行定义转换器
		 */
		HttpMessageConverter<ExceptionResponseObject> exceptionResponseObjectConverter = new HttpMessageConverter<ExceptionResponseObject>() {

			/**
			 * 判断能否转换输入内容的类型
			 */
			@Override
			public boolean canRead(Class<?> clazz, MediaType mediaType) {
				return false;
			}

			/**
			 * 判断能否将内容输出为指定格式
			 */
			@Override
			public boolean canWrite(Class<?> clazz, MediaType mediaType) {
				// TODO Auto-generated method stub
				if(clazz.equals(ExceptionResponseObject.class)) {
					return true;
				}
				return false;
			}

			/**
			 * 支持的类型列表
			 */
			@Override
			public List<MediaType> getSupportedMediaTypes() {
				// TODO Auto-generated method stub
				List<MediaType> list = new ArrayList<>();
				list.add(MediaType.ALL);
				return list;
			}

			/**
			 * 读取输入内容，并转换为指定对象
			 */
			@Override
			public ExceptionResponseObject read(Class<? extends ExceptionResponseObject> clazz, HttpInputMessage inputMessage)
					throws IOException, HttpMessageNotReadableException {
				// TODO Auto-generated method stub
				
				return null;
			}

			/**
			 * 将对象输出为指定字符串，使用CustomJsonComponent中定义的针对ExceptionResponseObject对象的序列化方法
			 */
			@Override
			public void write(ExceptionResponseObject t, MediaType contentType, HttpOutputMessage outputMessage)
					throws IOException, HttpMessageNotWritableException {

				/**
				 * CustomJsonComponent类中使用@JsonComponent标注了ExceptionResponseObject对象自定义的序列化方法
				 */
				String str = objectMapper.writeValueAsString(t);
				
				outputMessage.getBody().write(str.toString().getBytes());
			}

		};

		return exceptionResponseObjectConverter;
	}


}
