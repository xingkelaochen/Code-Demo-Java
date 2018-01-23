package com.xingkelaochen.codedemo.springboot.guide.data;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 注解@Service标注此类为服务层，得益于JpaRepository，进行数据仓库操作很简单
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
	 * 新增用户
	 * @return
	 */
	public User saveUser(String name,int age ) {
		
		User user = new User(name,age);
		
		user = userRepository.save(user);
		
		return user;
	}
	
	/**
	 * 主键查询用户
	 * @param id
	 * @return
	 */
	public Optional<User> findUser(String id) {
		
		// 以Id为条件查询对象，对象有可能为空，返回Optional对象
		Optional<User> user = userRepository.findById(id);
		
		return user;
	}
	
	
	/**
	 * 查询所有用户列表
	 * @return
	 */
	public List<User> findAllUsers() {
		
		// 获取所有用户列表
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	/**
	 * 调用自定义的enable方法
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User enableUser(User user) throws Exception {
		
		user = userRepository.enable(user);
		
		return user;
	}
	
	/**
	 * 使用UserRepository接口中申明的findByName方法查询用户列表
	 * @param name
	 * @return
	 */
	public List<User> findUsersByName(String name){
		
		return userRepository.findByName(name);
		
	}
}
