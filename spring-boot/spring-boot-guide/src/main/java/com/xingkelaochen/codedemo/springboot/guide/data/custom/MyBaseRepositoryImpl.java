package com.xingkelaochen.codedemo.springboot.guide.data.custom;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * ʵ���Զ����MyBaseRepository�ӿ�
 * ��@Configuration������ʹ��@EnableJpaRepositories(repositoryBaseClass = MyBaseRepositoryImpl.class)ָ��ȫ��JpaRepositoryʹ�ô���չʵ��
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
		
		Method setEnabled = t.getClass().getMethod("setEnabled",boolean.class);
		
		if(setEnabled!=null) {
			
			setEnabled.invoke(t, true);
			
			t = em.merge(t);
			
			return t;
		}
		
		return null;
	}

	@Override
	public T disable(T t) throws Exception {
		
		Method setEnabled = t.getClass().getMethod("setEnabled",boolean.class);
		
		if(setEnabled!=null) {
			
			setEnabled.invoke(t, false);
			
			t = em.merge(t);
			
			return t;
		}
		
		return null;
	}

}
