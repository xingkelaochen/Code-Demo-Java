package com.xingkelaochen.codedemo.java.java8.defaultMethod;

/**
 * �ӿ�Ĭ�Ϸ�����ʾ
 * 
 * <p>
 * 	ʵ����������ͬǩ��Ĭ�Ϸ����Ľӿڣ����<ʵ�ֶ���ӿڣ��ӿ����з���ǩ��һ�µ�Ĭ�Ϸ�������ʱʵ�������α���������޷�ѡ��>������
 * </p>
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
public class Demo1 implements InterfaceA, InterfaceB {

	/**
	 * ��Ȼ�����ϱ�����Լ̳нӿ��ж����helloĬ�Ϸ����������������ӿڳ�ͻ�����Ա�����д���������μ̳е�����Ҳ��Ҫָ��ʵ�֡�
	 * ����ʹ����������InterfaceA.super.hello()�ķ�ʽָ��ʹ���ĸ��ӿڵ�Ĭ��ʵ�֡�
	 */
	@Override
	public void hello() {
		InterfaceA.super.hello();
	}
	
	public static void main(String[] args) {
		
		Demo1 demo1 = new Demo1();
		/**
		 * [��������ȼ����]
		 * ����ָ����ʵ�֣�ʹ��InterfaceA��hello()��������� hello A
		 */
		demo1.hello();
	}
}
