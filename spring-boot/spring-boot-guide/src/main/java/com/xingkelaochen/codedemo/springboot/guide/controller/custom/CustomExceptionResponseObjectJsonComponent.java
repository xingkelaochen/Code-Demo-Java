package com.xingkelaochen.codedemo.springboot.guide.controller.custom;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.xingkelaochen.codedemo.springboot.guide.controller.error.ExceptionResponseObject;

/**
 * 
 * Spring Boot默认使用jackson作为json的序列化与反序列化工具，可以进行定制化。
 * 
 * <p>
 * 	使用@JsonComponent标注，并重写JsonSerializer与JsonDeserializer的serialize/deserialize方法。须配合Spring容器中的ObjectMapper对象进行json字符串的序列化与反序列化。
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
@JsonComponent
public class CustomExceptionResponseObjectJsonComponent {
	
	public static class Serializer extends JsonSerializer<ExceptionResponseObject> {

		/**
		 * 将ExceptionResponseObject对象序列化为{"errorInfo":"msg[status]"}格式的json字符串
		 */
		@Override
		public void serialize(ExceptionResponseObject arg0, JsonGenerator arg1, SerializerProvider arg2)
				throws IOException {

			arg1.writeStartObject();
			
			arg1.writeStringField("errorInfo", arg0.getMsg()+"["+arg0.getStatus()+"]");
			
			arg1.writeEndObject();
			
		}
	}

	public static class Deserializer extends JsonDeserializer<ExceptionResponseObject> {

		@Override
		public ExceptionResponseObject deserialize(JsonParser arg0, DeserializationContext arg1)
				throws IOException, JsonProcessingException {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
