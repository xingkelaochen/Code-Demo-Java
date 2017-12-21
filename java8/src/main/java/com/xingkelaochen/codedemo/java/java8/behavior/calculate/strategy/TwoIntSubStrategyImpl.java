package com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy;

/**
 * 
 * 减法计算策略的实现类
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
public class TwoIntSubStrategyImpl implements ITwoIntCalculateStrategy {

	@Override
	public int calculate(int a, int b) {
		
		return a-b;
	}

}
