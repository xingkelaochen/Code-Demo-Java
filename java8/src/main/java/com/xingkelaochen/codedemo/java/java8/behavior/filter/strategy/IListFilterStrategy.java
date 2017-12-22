package com.xingkelaochen.codedemo.java.java8.behavior.filter.strategy;

/**
 * 学生列表筛选接口
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
	 * test方法判定是否符合筛选条件
	 * @param t 参数对象
	 * @param value 判断条件值
	 * @return true/false
	 */
	public boolean test(T t,P value);
	
}
