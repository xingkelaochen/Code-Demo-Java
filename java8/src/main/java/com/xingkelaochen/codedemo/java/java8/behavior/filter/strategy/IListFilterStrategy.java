package com.xingkelaochen.codedemo.java.java8.behavior.filter.strategy;

/**
 * ѧ���б�ɸѡ�ӿ�
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
 */
public interface IListFilterStrategy<T,P> {

	/**
	 * test�����ж��Ƿ����ɸѡ����
	 * @param t ��������
	 * @param value �ж�����ֵ
	 * @return true/false
	 */
	public boolean test(T t,P value);
	
}
