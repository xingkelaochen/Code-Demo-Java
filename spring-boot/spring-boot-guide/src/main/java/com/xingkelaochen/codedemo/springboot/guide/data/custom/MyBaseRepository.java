package com.xingkelaochen.codedemo.springboot.guide.data.custom;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * 默认的，各Model的Respository接口只需要继承内置的类似CurdRepository、PagingAndSortingRepository、JpaRepository等就可以实现基本的数据库操作，
 * 并且可以在各自的Repository中按照约定规则进行定制化的操作方法申明，但我们也希望能有一个上级的Repository能够提供一些统一的数据库操作方法。
 * 
 * <p>
 * 注解@NoRepositoryBean标记本接口不会进行实例化，将由继承它的子接口进行申明。
 * </p>
 *
 * <p>
 * 自定义的数据库操作方法，如果不满足约定的规则，则需要使用@Query进行标记，如果是增、删、改的操作，则需要使用@Modifying标注。
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
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface MyBaseRepository<T,ID extends Serializable> extends JpaRepository<T, ID> {

	/**
	 * 自定义方法，将对象修改为开启状态
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@Modifying
	@Query(value="update #{#entityName} set enabled=true")
	T enable(T t) throws Exception ;
	
	/**
	 * 自定义方法，将对象修改为无效状态
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@Modifying
	@Query(value="update #{#entityName} set enabled=false")
	T disable(T t) throws Exception ;
}
