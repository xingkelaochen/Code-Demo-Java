package com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy;

/**
 * 
 * 计算策略接口定义
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
public interface ITwoIntCalculateStrategy {
	
	/**
	 * 接收两个参数，进行业务计算，返回计算结果
	 * @param a 整数a
	 * @param b 整数b
	 * @return 运算结果
	 */
	public int calculate(int a,int b);

}
