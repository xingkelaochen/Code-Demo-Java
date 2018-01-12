package com.xingkelaochen.codedemo.springboot.guide.data.custom;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.io.Serializable;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * 实现自定义的MyBaseRepository接口
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
public class MyBaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements MyBaseRepository<T, ID> {

	private EntityManager em;
	
	public MyBaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
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
	public T diable(T t) throws Exception {
		BeanInfo bean = Introspector.getBeanInfo(t.getClass());
		
		Method setEnabled = t.getClass().getMethod("setEnabled", null);
		
		if(setEnabled!=null && setEnabled.isAccessible()) {
			setEnabled.invoke(false, t);
			
			t = em.merge(t);
			
			return t;
		}
		
		return null;
	}

}
