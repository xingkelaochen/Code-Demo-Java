package com.xingkelaochen.codedemo.springboot.guide.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.xingkelaochen.codedemo.springboot.guide.data.custom.MyBaseRepository;

/**
 * 
 * 以注解@Repository标注的接口，通过继承JpaRepository接口，拥有了众多通用的数据库仓库的操作方法。而此接口并不用自行实现，一切都由Spring Data动态生成。
 *
 * 
 * <p>
 * 	此接口继承了自定定义的MyBaseRepository接口，指定了JpaRepository的实现为MyBaseRepositoryImpl。
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
	 * 
	 * 注解@Nullable与@NonNull可以用在方法或参数上，校验目标是否可以为null。@NonNullApi注解用在包级别的package-info.java文件中，将由方法级别的@Nullable与@NonNull覆盖。
	 * 
	 * @param name
	 * @return
	 */
	@Nullable
	public List<User> findByName(@NonNull String name);
	
	/**
	 * 查询用户(User)联系信息(ContactInfo)的地址等于传入参数的列表。为了消除歧义使用_将两个对象隔开
	 * @param address
	 * @return
	 */
	public List<User> findByContactInfo_Address(String address);
	
	// 按照约定规则建立数据操作方法
	
	/**
	 * findByXxxAndXxx规则，查询以两个属性为条件的用户列表
	 * 
	 * @param name
	 * @param sex
	 * @return
	 */
	public List<User> findByNameAndSex(String name,String sex);
	
	/**
	 * findDistinctByXxxOrXxx规则，查询以指定属性为“或”关系的条件，并且不相同的用户列表
	 * @param name
	 * @param sex
	 * @return
	 */
	public List<User> findDistinctUserByNameOrSex(String name,String sex);
	
	/**
	 * findByXxxOrderByXxxAsc/Desc规则，排序查询用户列表
	 * @param age
	 * @return
	 */
	public List<User> findByAgeOrderByAgeAsc(int age);
	
	/**
	 * 传入Pageable分页参数，进行分页查询
	 * Page的父类是Slice
	 * 
	 * @param name
	 * @param pageable
	 * @return
	 */
	public Page<User> findByName(String name,Pageable pageable);
	
	/**
	 * 传入Pageable分页参数，进行分页查询
	 * Slice是Page的父类
	 * 
	 * @param name
	 * @param pageable
	 * @return
	 */
	 // public Slice<User> findByName(String name,Pageable pageable);
	
	/**
	 * 传入Sort对象，排序查询所有用户列表
	 * @param name
	 * @param sort
	 * @return
	 */
	public List<User> findByName(String name,Sort sort);
	
	/**
	 * findFirstByXxxOrderByXxxDesc，查询年龄最大的用户
	 * First与Top有相同的作用
	 * @return
	 */
	public User findFirstByOrderByAgeDesc();
	
	/**
	 * 查询年龄最小的用户
	 * @return
	 */
	public User findTopByOrderByAgeAsc();
	
	/**
	 * 查询年龄最小的前十位
	 * 
	 * @return
	 */
	public List<User> findTop10ByOrderByAgeAsc();
	
	/**
	 * NotNull条件限定，返回Java8支持的Stream
	 * @return
	 */
	public Stream<User> findByNameNotNull();
	
	/**
	 * /**
	 * 使用注解@Query标注的方法将不使用默认规则解释，将使用给定的语句进行排序查询。（参数使用?1替代）
	 * <p>
	 * nativeQuery为true的话，将使用原生态的SQL语句
	 * 语句中使用#{#entityName}表达式，将使用Repository给定Entity的value属性，此处是User
	 * </p>
	 * 
	 * @param name
	 * @param sort
	 * @return
	 */
	@Query(value="select u from #{#entityName} u where u.name like %?1%",nativeQuery=false)
	public Stream<User> findByCustomQueryAndStream(String name,Sort sort);
	
	/**
	 * 使用注解@Async标注，返回异步执行对象，支持Future、CompletableFuture、ListenableFuture
	 * @param name
	 * @return
	 */
	@Async
	public CompletableFuture<User> findAsyncByName(String name);
}
