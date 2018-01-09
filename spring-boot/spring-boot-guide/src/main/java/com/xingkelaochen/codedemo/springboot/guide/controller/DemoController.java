package com.xingkelaochen.codedemo.springboot.guide.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xingkelaochen.codedemo.springboot.guide.configuration.MyConfigurationBinding;
import com.xingkelaochen.codedemo.springboot.guide.configuration.MyConfigurationBinding.Info;

/**
 * Spring Boot��������ʾ
 * 
 * <p>
 * Ӧ������spring-boot-starter-web���⽫����Spring Boot����Spring MVC���Զ������á�
 * <ul>
 * 	�Զ�������Ĭ�Ͻ��������в�����
 * 	<li>Inclusion of ContentNegotiatingViewResolver and BeanNameViewResolver beans.</li>
 *	<li>Support for serving static resources, including support for WebJars (covered later in this document)).</li>
 *	<li>Automatic registration of Converter, GenericConverter, and Formatter beans.</li>
 *	<li>Support for HttpMessageConverters (covered later in this document).</li>
 *	<li>Automatic registration of MessageCodesResolver (covered later in this document).</li>
 *	<li>Static index.html support.</li>
 *	<li>Custom Favicon support (covered later in this document).</li>
 *	<li>Automatic use of a ConfigurableWebBindingInitializer bean (covered later in this document).</li>
 *   </ul>
 * </p>
 * <p>
 * ע��@RestController����������һ��REST���Ŀ�����������@RequestMapping��������http����ķ����·��
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
@RestController
@RequestMapping(value="/demo")
public class DemoController {
	
	@Autowired
	private MyConfigurationBinding myConfigurationBinding;

	/**
	 * һ������URIΪ/demo/printMyConfiguratioinInfo��Rest����ӿڣ���ָ�����ܵ���������Ϊget��post�����������ļ���Info�����Զ�ת����Ϊjson�ַ������ء�
	 * @return
	 */
	@RequestMapping(value="printMyConfiguratioinInfo",method= {RequestMethod.GET,RequestMethod.POST})
	public Info printMyConfiguratioinInfo() {
		
		Info info = myConfigurationBinding.getInfo();
		
		return info;
	}
	
	@RequestMapping(value="getApplicationProperties",method= {RequestMethod.GET,RequestMethod.POST})
	public Properties getApplicationProperties() throws IOException {
		
		Properties application = new Properties();
		
		InputStream inputStream = this.getClass().getResourceAsStream("/application.yml");
		
		application.load(inputStream);
		return application;
	}
	
}
