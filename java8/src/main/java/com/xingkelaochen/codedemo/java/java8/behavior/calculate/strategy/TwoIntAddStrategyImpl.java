package com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy;

/**
 * 
 * �ӷ�������Ե�ʵ����
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
public class TwoIntAddStrategyImpl implements ITwoIntCalculateStrategy {

	@Override
	public int calculate(int a, int b) {
		
		return a+b;
	}

}
