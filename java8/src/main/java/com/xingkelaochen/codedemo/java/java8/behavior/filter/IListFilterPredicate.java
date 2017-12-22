package com.xingkelaochen.codedemo.java.java8.behavior.filter;

/**
 * 
 * ʹ��JDK8����Ϊ����������������ʽ�ӿ�
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
public interface IListFilterPredicate<T> {

	/**
	 * ����ʹ�ú���ʽ��̣��˺���ʽ�ӿ�ֻ�����ж���Ŀ����󣬶��ж�ֵ����lambda���ʽ->��������ߵ��ж�����������
	 * 
	 * @param t ��Ҫɸѡ���ϵĶ�������
	 * @return true/false
	 */
	public boolean test(T t);
	
}
