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
 * Spring Boot�ṩ��@MockBean������һ����Mockito��װ��ģ��Bean�������Bean����Spring�����д��ڵ��κο������õĶ���
 * 
 * <p>
 * 	Mockito�ṩ�����ְ�װ���ͣ��ֱ���@MockBean��@SpyBean������������@MockBean������Bean���Ǵ�׮�ķ�����ʹ��Ĭ��ֵ���أ�û�з���ֵ�ķ�����ִ���κη����壩����@SpyBean�෴��
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
		
		// ����ģ�����userService��ִ��findAllUsers()����ʱ���صĶ���
		BDDMockito.given(userService.findAllUsers()).willReturn(users);
		Assert.assertEquals(1, userService.findAllUsers().size());

		// ��֤�Ƿ������һ��findAllUsers()����
		BDDMockito.verify(userService,times(1)).findAllUsers();

	}
	
}
