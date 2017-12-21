package com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy;

/**
 * 
 * ��������ֵ���㻷���࣬��������ʵ��������ʱָ�����Ե�ʵ������
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
