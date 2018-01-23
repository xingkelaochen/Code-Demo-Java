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
@SpringBootTest(classes= {Application.class})
@DataJpaTest
public class JpaTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void test() {
		
		// 新建一个User对象，persist方法将此对象转换为managed( 托管 ) 状态，这样就可以使用repository进行相关查询
		this.entityManager.persist(new User("xingkelaochen",33));
		
		List<User> users = userRepository.findByName("xingkelaochen");
		
		Assert.assertEquals(users.size(), 1);
		
	}
	
}
