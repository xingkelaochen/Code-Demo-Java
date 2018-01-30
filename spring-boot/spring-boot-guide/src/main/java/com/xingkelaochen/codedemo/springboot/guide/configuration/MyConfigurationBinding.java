package com.xingkelaochen.codedemo.springboot.guide.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 
 * �������ļ��е���������ӳ��󶨵�������У�Ҳ���Խ������Եļ��顣
 * 
 * <p>
 * 	���ְ��Ǹ��������ļ��е��������ơ���㼶��ϵ����Լ��ӳ��ġ�
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
@Component
@ConfigurationProperties(prefix="my")
// ʹ��@Validated����@ConfigurationProperties�����ݰ�У�鹦��
@Validated
public class MyConfigurationBinding {

	@NotNull
	private final List<String> servers = new ArrayList<String>();
	
	@Valid
	private final Info info = new Info();
	
	public List<String> getServers(){
		return this.servers;
	}

	public Info getInfo() {
		return info;
	}
	
	public static class Info{
		
		@NotNull
		private String name;
		
		private String sex;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		
		
	}
	
}
