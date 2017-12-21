package com.xingkelaochen.codedemo.java.java8.behavior.calculate;

import java.util.function.IntBinaryOperator;
import com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy.ITwoIntCalculateStrategy;
import com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy.TwoIntAddStrategyImpl;
import com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy.TwoIntCalculateContext;
import com.xingkelaochen.codedemo.java.java8.behavior.calculate.strategy.TwoIntSubStrategyImpl;

/**
 * 
 * ��ʾ����ҵ����Ϊ���ݵ�ʵ�ַ�ʽ��������Ϊ�������ݽ�
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
	 * ʹ�ò�������ʶ��ȷ��ҵ�������ͣ���������ʹ��if����жϽ���ҵ���� <br />
	 * ����ʵ�ַ�ʽ������ͬ���͵�ҵ�������ͬһ���������У�Υ������ԭ�򣬷ǳ������ڴ��������ҵ�����䡣
	 * 
	 * @param a
	 *            ����a
	 * @param b
	 *            ����b
	 * @param operate
	 *            ������ʶ add:�ӷ� sub:���� ����
	 * @return ������
	 */
	public int ancient(int a, int b, String operate) {

		if (operate.equals("add")) {
			return a + b;
		}

		if (operate.equals("sub")) {
			return a - b;
		}

		// ����

		return 0;
	}

	/**
	 * ʹ�������ڲ��࣬��������ʹ��strategy���ж���Ĳ��Խӿڡ� <br />
	 * ��Ȼ����Ҫ�����ҵ��ʵ���࣬�����Ǳ��ⲻ�˺ܶ�ģ����룬���������ԭ�������ʵ�뱾��ancient��������Ҳ������
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

		// ����

		return 0;
	}

	/**
	 * ʹ�ù���ģʽ����������ʹ��strategy���ж���Ĳ��Խӿ���ʵ���ࡣ <br />
	 * ���ַ�ʽ��Ȼʹҵ�����ʵ���˷����������������д���������Υ������ԭ�򣨼�����factoryCreate���������뱾��ancient�ķ����ϸ���������˵�����ޱ�������
	 * 
	 * @param a
	 *            ����a
	 * @param b
	 *            ����b
	 * @param operate
	 *            ������ʶ add:�ӷ� sub:���� ����
	 * @return ������
	 */
	public int factoryPattern(int a, int b, String operate) {

		ITwoIntCalculateStrategy strategy = factoryCreate(operate);

		return strategy.calculate(a, b);

	}

	/**
	 * ����������������
	 * 
	 * @param operate
	 *            ������ʶ add:�ӷ� sub:���� ����
	 * @return ��ֵ�������
	 */
	private ITwoIntCalculateStrategy factoryCreate(String operate) {

		if (operate.equals("add")) {
			return new TwoIntAddStrategyImpl();
		}

		if (operate.equals("sub")) {
			return new TwoIntSubStrategyImpl();
		}

		// ����

		return null;
	}

	/**
	 * ʹ�ò���ģʽ����Ϊ�����ʵ�ַ�ʽ�� <br />
	 * ����Ϊÿһ��ҵ����ҪдITwoIntCalculateStrategy�ӿڵ�ʵ���࣬���ҵ��÷��Ĵ���ɶ��Բ�̫�ã�ֻ�����Զ���ķ������ƣ������޷��ܺõ��˽�ҵ����ʲô����
	 * 
	 * @param a
	 *            ����a
	 * @param b
	 *            ����b
	 * @param strategy
	 *            ITwoIntCalculateStrategy��ʵ����
	 * @return ������
	 */
	public int strategyPattern(int a, int b, ITwoIntCalculateStrategy strategy) {

		TwoIntCalculateContext context = new TwoIntCalculateContext(strategy);

		return context.calculate(a, b);
	}

	/**
	 * ʹ��jdk1.8�����ԣ���Ϊ�������� <br />
	 * ֻ�趨������ӿڲ���Ҫʵ���࣬��ֻ����ITwoIntCalculateStrategy�����Ҷ���һ��ʹ�ô˽ӿ�Ϊ���������㷽����
	 * 
	 * @param a
	 *            ����a
	 * @param b
	 *            ����b
	 * @return ������
	 */
	private int calculate(int a, int b, ITwoIntCalculateStrategy p) {

		return p.calculate(a, b);
	}

	/**
	 * ʹ��jdk1.8���á�����ʽ�ӿڡ� <br />
	 * ��ʵ��JDK1.8�У�TwoIntCalculateStrategy�ӿ�Ҳ�ɲ��ض��壬����ʹ�����õ�IntBinaryOperator����ʽ�ӿڡ�
	 * 
	 * @param a
	 *            ����a
	 * @param b
	 *            ����b
	 */
	private int calculate2(int a, int b, IntBinaryOperator o) {
		return o.applyAsInt(a, b);
	}

	/**
	 * ʹ����Ϊ�������ڵ��÷�ֱ��ʹ��lambda������Ϊ���ݣ��ڵ��÷�Ҳֱ�۵�֪��������ʲô����
	 * 
	 * @param a
	 *            ����a
	 * @param b
	 *            ����b
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

		// ���µ���calculate2��������Ϊ��������IntBinaryOperator�����ػ���ĺ����ӿ�
		
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
		
		// ֱ����������ʽ�ӿ�,IntBinaryOperator����ǩ������Ϊint applyAsInt(int arg0, int arg1)
		IntBinaryOperator bi = (x,y)->a+b;
		System.out.println(a+"+"+b+"="+bi.applyAsInt(a, b));
	}

	public static void main(String[] args) {

		TwoIntCalculateDemo demo = new TwoIntCalculateDemo();

		demo.behavioralParameterization(1, 2);

	}

}
