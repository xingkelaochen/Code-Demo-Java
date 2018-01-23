package com.xingkelaochen.codedemo.springboot.guide.data;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * ע��@Service��ע����Ϊ����㣬������JpaRepository���������ݲֿ�����ܼ�
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
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * �����û�
	 * @return
	 */
	public User saveUser(String name,int age ) {
		
		User user = new User(name,age);
		
		user = userRepository.save(user);
		
		return user;
	}
	
	/**
	 * ������ѯ�û�
	 * @param id
	 * @return
	 */
	public Optional<User> findUser(String id) {
		
		// ��IdΪ������ѯ���󣬶����п���Ϊ�գ�����Optional����
		Optional<User> user = userRepository.findById(id);
		
		return user;
	}
	
	
	/**
	 * ��ѯ�����û��б�
	 * @return
	 */
	public List<User> findAllUsers() {
		
		// ��ȡ�����û��б�
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	/**
	 * �����Զ����enable����
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User enableUser(User user) throws Exception {
		
		user = userRepository.enable(user);
		
		return user;
	}
	
	/**
	 * ʹ��UserRepository�ӿ���������findByName������ѯ�û��б�
	 * @param name
	 * @return
	 */
	public List<User> findUsersByName(String name){
		
		return userRepository.findByName(name);
		
	}
}
