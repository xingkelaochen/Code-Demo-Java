package test.xingkelaochen.codedemo.springboot.guide;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xingkelaochen.codedemo.springboot.guide.Application;
import com.xingkelaochen.codedemo.springboot.guide.data.User;
import com.xingkelaochen.codedemo.springboot.guide.data.UserRepository;

/**
 * SpringBoot�ṩ@DataJpaTestע�����Data Jpa�Ĳ��ԣ��⽫ɨ��@Entity��ע��ʵ�壬�ṩһ�������ڴ�Ƕ��ʽ�����ݿ⡣
 * Ĭ�ϵģ����Է����ǻ�������ģ�������ÿ�����Է���������ع����ݿ����������ע��һ��TestEntityManager�������ڽ��в�����ƣ���û��ʹ��@DataJpaTest������ʹ��@AutoConfigureTestEntityManagerע��ע��TestEntityManager���󣩡�
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
@SpringBootTest(classes= {Application.class})
@DataJpaTest
public class JpaTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void test() {
		
		// �½�һ��User����persist�������˶���ת��Ϊmanaged( �й� ) ״̬�������Ϳ���ʹ��repository������ز�ѯ
		this.entityManager.persist(new User("xingkelaochen",33));
		
		List<User> users = userRepository.findByName("xingkelaochen");
		
		Assert.assertEquals(users.size(), 1);
		
	}
	
}
