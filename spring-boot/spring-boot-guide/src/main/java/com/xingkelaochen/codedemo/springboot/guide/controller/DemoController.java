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
 * Spring Boot控制器演示
 * 
 * <p>
 * 应用依赖spring-boot-starter-web，这将开启Spring Boot对于Spring MVC的自动化配置。
 * <ul>
 * 	自动化配置默认进行了下列操作：
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
 * 注解@RestController声明此类是一个REST风格的控制器，并用@RequestMapping配置用于http请求的服务根路由
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
	 * 一个访问URI为/demo/printMyConfiguratioinInfo的Rest服务接口，并指定接受的请求类型为get与post。将绑定属性文件的Info对象自动转换成为json字符串返回。
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
