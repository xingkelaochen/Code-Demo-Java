package test.xingkelaochen.codedemo.springboot.guide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.xingkelaochen.codedemo.springboot.guide.Application;
import com.xingkelaochen.codedemo.springboot.guide.configuration.ApplicationConfinguration;
import com.xingkelaochen.codedemo.springboot.guide.configuration.MyConfigurationBinding;
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
@DataJpaTest
// ����ʹ����@DataJpaTestע�⣬���Ի������������ָ��֮��������ࡣ
// ����UserRepository�̳����Զ����MyBaseRepository�ӿڣ���Ҫָ��ApplicationConfinguration�����࣬ʹRepositoryʹ��ָ����ʵ�֡�
@ContextConfiguration(classes= {Application.class,ApplicationConfinguration.class})
@Transactional
public class JpaTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void test() throws Exception {
		
		// �½�һ��User����persist�������˶���ת��Ϊmanaged( �й� ) ״̬�������Ϳ���ʹ��repository������ز�ѯ
		User user = this.entityManager.persist(new User("xingkelaochen",33));
		
		assertEquals(false,user.isEnabled());
		
		user = userRepository.findById(user.getId()).get();
		assertNotNull(user);
		
		List<User> users = userRepository.findByName("xingkelaochen");
		
		assertEquals(users.size(), 1);
		
		user = userRepository.enable(user);
		
		assertEquals(true, user.isEnabled());
		
	}
	
}
