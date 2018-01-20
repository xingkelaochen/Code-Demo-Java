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
	 * SpringBoot Ĭ��ʹ��HttpMessageConverter����http��������Ӧ����Ϣת������������ܱ��Զ��Ļ�Ϊjson��ʽ���ַ������ַ���Ĭ�ϱ����ʽΪUTF-8��
	 * <p>
	 * 	��������HttpMessageConverter��HttpMessageConverters���󣬽�һЩ�Զ����ת������ӵ�SpringBootת�������У���Щת����������Ԥ�����������/������ݽ���ת����
	 * </p>
	 * ʹ��@Bean��ע�˷�����ʹSpringʵ�����˶��󣬲���ʹ�ô˷�����ȡ�������
	 * @return
	 */
	@Bean
	public HttpMessageConverter<ExceptionResponseObject> customExceptionResponseObjectConverter() {
		
		/**
		 * ExceptionResponseObject���Ͳ�ʹ��Ĭ�ϵ�json��ʽ�������ж���ת����
		 */
		HttpMessageConverter<ExceptionResponseObject> exceptionResponseObjectConverter = new HttpMessageConverter<ExceptionResponseObject>() {

			/**
			 * �ж��ܷ�ת���������ݵ�����
			 */
			@Override
			public boolean canRead(Class<?> clazz, MediaType mediaType) {
				return false;
			}

			/**
			 * �ж��ܷ��������Ϊָ����ʽ
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
			 * ֧�ֵ������б�
			 */
			@Override
			public List<MediaType> getSupportedMediaTypes() {
				// TODO Auto-generated method stub
				List<MediaType> list = new ArrayList<>();
				list.add(MediaType.ALL);
				return list;
			}

			/**
			 * ��ȡ�������ݣ���ת��Ϊָ������
			 */
			@Override
			public ExceptionResponseObject read(Class<? extends ExceptionResponseObject> clazz, HttpInputMessage inputMessage)
					throws IOException, HttpMessageNotReadableException {
				// TODO Auto-generated method stub
				
				return null;
			}

			/**
			 * ���������Ϊָ���ַ�����ʹ��CustomJsonComponent�ж�������ExceptionResponseObject��������л�����
			 */
			@Override
			public void write(ExceptionResponseObject t, MediaType contentType, HttpOutputMessage outputMessage)
					throws IOException, HttpMessageNotWritableException {

				/**
				 * CustomJsonComponent����ʹ��@JsonComponent��ע��ExceptionResponseObject�����Զ�������л�����
				 */
				String str = objectMapper.writeValueAsString(t);
				
				outputMessage.getBody().write(str.toString().getBytes());
			}

		};

		return exceptionResponseObjectConverter;
	}


}
