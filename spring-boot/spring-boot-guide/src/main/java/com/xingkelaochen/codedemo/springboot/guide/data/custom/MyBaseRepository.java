package com.xingkelaochen.codedemo.springboot.guide.data.custom;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * Ĭ�ϵģ���Model��Respository�ӿ�ֻ��Ҫ�̳����õ�����CurdRepository��PagingAndSortingRepository��JpaRepository�ȾͿ���ʵ�ֻ��������ݿ������
 * ���ҿ����ڸ��Ե�Repository�а���Լ��������ж��ƻ��Ĳ�������������������Ҳϣ������һ���ϼ���Repository�ܹ��ṩһЩͳһ�����ݿ����������
 * 
 * <p>
 * ע��@NoRepositoryBean��Ǳ��ӿڲ������ʵ���������ɼ̳������ӽӿڽ���
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
	 * �Զ��巽�����������޸�Ϊ����״̬
	 * @param t
	 * @return
	 * @throws Exception
	 */
	T enable(T t) throws Exception ;
	
	/**
	 * �Զ��巽�����������޸�Ϊ��Ч״̬
	 * @param t
	 * @return
	 * @throws Exception
	 */
	T diable(T t) throws Exception ;
}
