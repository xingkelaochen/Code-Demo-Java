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
 * SpringBoot提供@DataJpaTest注解进行Data Jpa的测试，这将扫描@Entity标注的实体，提供一个基于内存嵌入式的数据库。
 * 默认的，测试方法是基于事务的，并且在每个测试方法线束后回滚数据库操作。并且注入一个TestEntityManager对象，用于进行测试设计（若没有使用@DataJpaTest，可以使用@AutoConfigureTestEntityManager注解注入TestEntityManager对象）。
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
// 由于使用了@DataJpaTest注解，测试环境并不会加载指定之外的配置类。
// 所以UserRepository继承了自定义的MyBaseRepository接口，需要指定ApplicationConfinguration配置类，使Repository使用指定的实现。
@ContextConfiguration(classes= {Application.class,ApplicationConfinguration.class})
@Transactional
public class JpaTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void test() throws Exception {
		
		// 新建一个User对象，persist方法将此对象转换为managed( 托管 ) 状态，这样就可以使用repository进行相关查询
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
