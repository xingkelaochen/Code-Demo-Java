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
 * Spring BootĬ��ʹ��jackson��Ϊjson�����л��뷴���л����ߣ����Խ��ж��ƻ���
 * 
 * <p>
 * 	ʹ��@JsonComponent��ע������дJsonSerializer��JsonDeserializer��serialize/deserialize�����������Spring�����е�ObjectMapper�������json�ַ��������л��뷴���л���
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
		 * ��ExceptionResponseObject�������л�Ϊ{"errorInfo":"msg[status]"}��ʽ��json�ַ���
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
