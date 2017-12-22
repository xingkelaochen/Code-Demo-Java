package com.xingkelaochen.codedemo.java.java8.behavior.filter;

/**
 * 
 * 使用JDK8的行为参数化，申明函数式接口
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
	 * 由于使用函数式编程，此函数式接口只接受判定的目标对象，而判定值将由lambda表达式->操作符后边的判定条件所包含
	 * 
	 * @param t 需要筛选集合的对象类型
	 * @return true/false
	 */
	public boolean test(T t);
	
}
