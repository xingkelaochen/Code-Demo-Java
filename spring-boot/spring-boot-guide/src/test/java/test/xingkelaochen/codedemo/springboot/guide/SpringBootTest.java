package test.xingkelaochen.codedemo.springboot.guide;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.xingkelaochen.codedemo.springboot.guide.Application;
import com.xingkelaochen.codedemo.springboot.guide.data.User;
import com.xingkelaochen.codedemo.springboot.guide.data.UserService;

/**
 * SpringBoot提供@SpringBootTest注解，用于提供本SpringBoot应用测试的环境。
 * 这需要spring-boot-starter-test起步依赖，测试类中需要@RunWith(SpringRunner.class)指定Junit运行器，使用@SpringBootTest或者@ContextConfiguration的classes指定配置类。
 * <p>
 * 	webEnvironment接受四个不同类型的Web环境设置参数：
 *  1. Mock 加载一个模拟的servlet环境，这不会运行内嵌的servelt容器。如果应用中不依赖servlet的API，则会创建一个非WEB应用的ApplicationContext环境。
 *  2. RANDOM_PORT 使用内嵌servlet容器，监听随机端口提供servlet环境支持。
 *  3. DEFINED_PORT 使用内嵌servlet容器，监听配置文件中指定的端口提供servlet环境支持。
 *  4. NONE 使用SpringApplication加载ApplicationContext环境，不提供任何servlet环境。
 * </p>
 *
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
@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes= {Application.class},webEnvironment=WebEnvironment.DEFINED_PORT)
public class SpringBootTest {
	
	/**
	 * 使用内部类加载测试环境下配置项，也可以使用@Import明确的指定测试配置类。
	 * <p>
	 * 针对Spring Boot Test的配置，在测试环境中会进行加载配置。在生产环境中，使用@SpringBootApplication启动会自动排除此注解标注的配置类。
	 * 如果直接使用@ComponentScan而没有使用@SpringBootApplication，则需要注册TypeExcludeFilter扣除这个配置。
	 * </p>
	 */
	@org.springframework.boot.test.context.TestConfiguration
	public class TestConfig{
		
		@Bean
		public String getTestMsg() {
			return "TestConfig";
		}
		
	}
	

	@Autowired
	private UserService userService;
	
	@Test
	public void testSaveUser() {
		
		User user = userService.saveUser("xingkelaochen",33);
		
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
	}
	
}
