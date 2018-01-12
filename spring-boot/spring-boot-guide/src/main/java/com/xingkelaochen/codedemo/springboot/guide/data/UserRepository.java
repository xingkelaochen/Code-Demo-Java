package com.xingkelaochen.codedemo.springboot.guide.data;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xingkelaochen.codedemo.springboot.guide.data.custom.MyBaseRepository;

/**
 * 
 * 以注解@Repository标注的接口，通过继承JpaRepository接口，拥有了众多通用的数据库仓库的操作方法。而此接口并不用自行实现，一切都由Spring Data动态生成。
 *
 * 
 * <p>
 * 	此接口继承了自定定义的MyBaseRepository接口
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
@Repository
public interface UserRepository extends MyBaseRepository<User, String>{

	/**
	 * 按照Repository的findByXxx规则，申明使用name进行查询的方法
	 * @param name
	 * @return
	 */
	public List<User> findByName(String name);
	
}
