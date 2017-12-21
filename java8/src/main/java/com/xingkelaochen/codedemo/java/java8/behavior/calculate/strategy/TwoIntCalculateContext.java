package com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy;

/**
 * 
 * 两个整数值计算环境类，调用者在实例化此类时指定策略的实现类型
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
public class TwoIntCalculateContext {
	
	private ITwoIntCalculateStrategy strategy;

	public TwoIntCalculateContext(ITwoIntCalculateStrategy strategy) {
		this.strategy = strategy;
	}
	
	public int calculate(int a,int b) {
		
		return this.strategy.calculate(a, b);
		
	}

	public void setStrategy(ITwoIntCalculateStrategy strategy) {
		this.strategy = strategy;
	}
	
}
