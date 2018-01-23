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
 * SpringBoot�ṩ@SpringBootTestע�⣬�����ṩ��SpringBootӦ�ò��ԵĻ�����
 * ����Ҫspring-boot-starter-test������������������Ҫ@RunWith(SpringRunner.class)ָ��Junit��������ʹ��@SpringBootTest����@ContextConfiguration��classesָ�������ࡣ
 * <p>
 * 	webEnvironment�����ĸ���ͬ���͵�Web�������ò�����
 *  1. Mock ����һ��ģ���servlet�������ⲻ��������Ƕ��servelt���������Ӧ���в�����servlet��API����ᴴ��һ����WEBӦ�õ�ApplicationContext������
 *  2. RANDOM_PORT ʹ����Ƕservlet��������������˿��ṩservlet����֧�֡�
 *  3. DEFINED_PORT ʹ����Ƕservlet���������������ļ���ָ���Ķ˿��ṩservlet����֧�֡�
 *  4. NONE ʹ��SpringApplication����ApplicationContext���������ṩ�κ�servlet������
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
	 * ʹ���ڲ�����ز��Ի����������Ҳ����ʹ��@Import��ȷ��ָ�����������ࡣ
	 * <p>
	 * ���Spring Boot Test�����ã��ڲ��Ի����л���м������á������������У�ʹ��@SpringBootApplication�������Զ��ų���ע���ע�������ࡣ
	 * ���ֱ��ʹ��@ComponentScan��û��ʹ��@SpringBootApplication������Ҫע��TypeExcludeFilter�۳�������á�
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
