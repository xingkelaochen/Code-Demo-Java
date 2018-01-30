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
 * 将配置文件中的属性配置映射绑定到类对象中，也可以进行属性的检验。
 * 
 * <p>
 * 	这种绑定是根据属性文件中的属性名称、与层级关系进行约定映射的。
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
// 使用@Validated开启@ConfigurationProperties的数据绑定校验功能
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
