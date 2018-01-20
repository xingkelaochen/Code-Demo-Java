package com.xingkelaochen.codedemo.springboot.guide.data.custom;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.io.Serializable;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 实现自定义的MyBaseRepository接口
 * 在@Configuration配置类使用@EnableJpaRepositories(repositoryBaseClass = MyBaseRepositoryImpl.class)指定全局JpaRepository使用此扩展实现
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
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public class MyBaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements MyBaseRepository<T, ID> {

	private EntityManager em;
	
	public MyBaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	@Override
	public T enable(T t) throws Exception {

		BeanInfo bean = Introspector.getBeanInfo(t.getClass());
		
		Method setEnabled = t.getClass().getMethod("setEnabled", null);
		
		if(setEnabled!=null && setEnabled.isAccessible()) {
			setEnabled.invoke(true, t);
			
			t = em.merge(t);
			
			return t;
		}
		
		return null;
	}

	@Override
	public T disable(T t) throws Exception {
		
		Method setEnabled = t.getClass().getMethod("setEnabled", null);
		
		if(setEnabled!=null && setEnabled.isAccessible()) {
			setEnabled.invoke(false, t);
			
			t = em.merge(t);
			
			return t;
		}
		
		return null;
	}

}
