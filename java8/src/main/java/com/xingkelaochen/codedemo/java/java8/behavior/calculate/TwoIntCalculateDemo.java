package com.xingkelaochen.codedemo.java.java8.behavior.calculate;

import java.util.function.IntBinaryOperator;
import com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy.ITwoIntCalculateStrategy;
import com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy.TwoIntAddStrategyImpl;
import com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy.TwoIntCalculateContext;
import com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy.TwoIntSubStrategyImpl;

/**
 * 
 * 演示几种业务行为传递的实现方式，逐步向行为参数化演进
 *
 * @author xingkelaochen
 * 
 *         <p>
 *         E-MAIL: admin@xingkelaochen.com <br />
 *         SITE: http://www.xingkelaochen.com
 *         </p>
 *
 */
public class TwoIntCalculateDemo {

	/**
	 * 使用参数做标识来确定业务处理类型，方法体中使用if语句判断进行业务处理。 <br />
	 * 这种实现方式，将不同类型的业务放置于同一个方法体中，违反开闭原则，非常不利于代码解耦与业务扩充。
	 * 
	 * @param a
	 *            整数a
	 * @param b
	 *            整数b
	 * @param operate
	 *            操作标识 add:加法 sub:减法 ……
	 * @return 运算结果
	 */
	public int ancient(int a, int b, String operate) {

		if (operate.equals("add")) {
			return a + b;
		}

		if (operate.equals("sub")) {
			return a - b;
		}

		// ……

		return 0;
	}

	/**
	 * 使用匿名内部类，这里暂且使用strategy包中定义的策略接口。 <br />
	 * 虽然不需要多余的业务实现类，但还是避免不了很多模板代码，并且在设计原则层面其实与本类ancient方法基本也无区别
	 * 
	 * @return
	 */
	public int anonymousInnerClass(int a, int b, String operate) {

		if (operate.equals("add")) {
			return (new ITwoIntCalculateStrategy() {

				@Override
				public int calculate(int a, int b) {

					return a + b;
				}
			}).calculate(a, b);
		}

		if (operate.equals("sub")) {
			return (new ITwoIntCalculateStrategy() {

				@Override
				public int calculate(int a, int b) {

					return a - b;
				}
			}).calculate(a, b);
		}

		// ……

		return 0;
	}

	/**
	 * 使用工厂模式，这里暂且使用strategy包中定义的策略接口与实现类。 <br />
	 * 这种方式虽然使业务代码实现了分离解耦，但工厂方法中创建对象还是违反开闭原则（见本类factoryCreate方法），与本类ancient的方法严格意义上来说基本无本质区别。
	 * 
	 * @param a
	 *            整数a
	 * @param b
	 *            整数b
	 * @param operate
	 *            操作标识 add:加法 sub:减法 ……
	 * @return 运算结果
	 */
	public int factoryPattern(int a, int b, String operate) {

		ITwoIntCalculateStrategy strategy = factoryCreate(operate);

		return strategy.calculate(a, b);

	}

	/**
	 * 工厂方法创建对象
	 * 
	 * @param operate
	 *            操作标识 add:加法 sub:减法 ……
	 * @return 数值运算对象
	 */
	private ITwoIntCalculateStrategy factoryCreate(String operate) {

		if (operate.equals("add")) {
			return new TwoIntAddStrategyImpl();
		}

		if (operate.equals("sub")) {
			return new TwoIntSubStrategyImpl();
		}

		// ……

		return null;
	}

	/**
	 * 使用策略模式，较为理想的实现方式。 <br />
	 * 但是为每一种业务都需要写ITwoIntCalculateStrategy接口的实现类，并且调用方的代码可读性不太好（只根据自定义的方法名称，他人无法很好的了解业务是什么）。
	 * 
	 * @param a
	 *            整数a
	 * @param b
	 *            整数b
	 * @param strategy
	 *            ITwoIntCalculateStrategy的实现类
	 * @return 运算结果
	 */
	public int strategyPattern(int a, int b, ITwoIntCalculateStrategy strategy) {

		TwoIntCalculateContext context = new TwoIntCalculateContext(strategy);

		return context.calculate(a, b);
	}

	/**
	 * 使用jdk1.8新特性，行为参数化。 <br />
	 * 只需定义运算接口不需要实现类，如只定义ITwoIntCalculateStrategy；并且定义一个使用此接口为参数的运算方法。
	 * 
	 * @param a
	 *            整数a
	 * @param b
	 *            整数b
	 * @return 运算结果
	 */
	private int calculate(int a, int b, ITwoIntCalculateStrategy p) {

		return p.calculate(a, b);
	}

	/**
	 * 使用jdk1.8内置“函数式接口” <br />
	 * 其实在JDK1.8中，TwoIntCalculateStrategy接口也可不必定义，可以使用内置的IntBinaryOperator函数式接口。
	 * 
	 * @param a
	 *            整数a
	 * @param b
	 *            整数b
	 */
	private int calculate2(int a, int b, IntBinaryOperator o) {
		return o.applyAsInt(a, b);
	}

	/**
	 * 使用行为参数化在调用方直接使用lambda进行行为传递，在调用方也直观的知道进行了什么操作
	 * 
	 * @param a
	 *            整数a
	 * @param b
	 *            整数b
	 */
	public void behavioralParameterization(int a, int b) {

		// add
		int result = calculate(a, b, (x, y) -> x + y);
		System.out.println(a + "+" + b + "=" + result);

		// sub
		result = calculate(a, b, (x, y) -> x - y);
		System.out.println(a + "-" + b + "=" + result);

		// mul
		result = calculate(a, b, (x, y) -> x * y);
		System.out.println(a + "*" + b + "=" + result);

		// other
		result = calculate(a, b, (x, y) -> x + y - (2 * x));
		System.out.println(a + "+" + b + "-(2*" + a + ")=" + result);

		// 以下调用calculate2，接收行为参数的是IntBinaryOperator内置特化版的函数接口
		
		// add
		int result2 = calculate2(a, b, (x, y) -> x + y);
		System.out.println(a + "+" + b + "=" + result);

		// sub
		result2 = calculate2(a, b, (x, y) -> x - y);
		System.out.println(a + "-" + b + "=" + result);

		// mul
		result2 = calculate2(a, b, (x, y) -> x * y);
		System.out.println(a + "*" + b + "=" + result);

		// other
		result2 = calculate2(a, b, (x, y) -> x + y - (2 * x));
		System.out.println(a + "+" + b + "-(2*" + a + ")=" + result);
		
		// 直接申明函数式接口,IntBinaryOperator函数签名描述为int applyAsInt(int arg0, int arg1)
		IntBinaryOperator bi = (x,y)->a+b;
		System.out.println(a+"+"+b+"="+bi.applyAsInt(a, b));
	}

	public static void main(String[] args) {

		TwoIntCalculateDemo demo = new TwoIntCalculateDemo();

		demo.behavioralParameterization(1, 2);

	}

}
