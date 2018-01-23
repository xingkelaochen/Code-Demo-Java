package test.xingkelaochen.codedemo.springboot.guide;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.xingkelaochen.codedemo.springboot.guide.Application;
import com.xingkelaochen.codedemo.springboot.guide.data.User;
import com.xingkelaochen.codedemo.springboot.guide.data.UserService;

/**
 * 
 * Spring Boot提供了@MockBean来申明一个被Mockito包装的模拟Bean对象，这个Bean是在Spring容器中存在的任何可以引用的对象。
 * 
 * <p>
 * 	Mockito提供了两种包装类型，分别用@MockBean与@SpyBean进行申明。以@MockBean申明的Bean，非打桩的方法将使用默认值返回（没有返回值的方法不执行任何方法体）；而@SpyBean相反。
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Application.class})
public class MockitoTest {

	@MockBean
	private UserService userService;
	
	@Test
	public void mockTest(){
		
		List<User> users = new ArrayList<>();
		users.add(new User("xingkelaochen",33));
		
		// 给定模拟对象userService在执行findAllUsers()方法时返回的对象
		BDDMockito.given(userService.findAllUsers()).willReturn(users);
		Assert.assertEquals(1, userService.findAllUsers().size());

		// 验证是否调用了一次findAllUsers()方法
		BDDMockito.verify(userService,times(1)).findAllUsers();

	}
	
}
