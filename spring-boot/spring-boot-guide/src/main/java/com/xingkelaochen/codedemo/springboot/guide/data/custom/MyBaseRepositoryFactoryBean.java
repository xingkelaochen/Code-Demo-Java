package com.xingkelaochen.codedemo.springboot.guide.data.custom;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * 自定义Repository的工厂类，配置@EnableJpaRepositories(repositoryFactoryBeanClass = MyBaseRepositoryFactoryBean.class) 注解，指定JpaRepository使用自定义的MyBaseRepositoryImpl实现
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
 * @param <S>
 * @param <ID>
 */
public class MyBaseRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {

	public MyBaseRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
		super(repositoryInterface);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		// TODO Auto-generated method stub
		return super.createRepositoryFactory(entityManager);
	}

	private static class MyBaseRepositoryFactory extends JpaRepositoryFactory{

		private EntityManager entityManager;
		
		public MyBaseRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return MyBaseRepositoryImpl.class;
		}
		
	}

}
