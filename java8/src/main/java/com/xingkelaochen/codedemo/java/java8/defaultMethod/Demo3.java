package com.xingkelaochen.codedemo.java.java8.defaultMethod;

/**
 * �ӿ�Ĭ�Ϸ�����ʾ
 * 
 * <p>
 * 	ʵ����������ͬǩ����Ĭ�Ϸ���������InterfaceC�̳�InterfaceB��д��hello()���������<ʵ�ֶ���ӿڣ��ӿ��м̳���дʱ����ѡ���ĸ�����ʵ��>������
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
public class Demo3 implements InterfaceB, InterfaceC {

	public static void main(String[] args) {
		
		Demo3 demo2 = new Demo3();
		
		/**
		 * [�ӽӿڸ�����ʵ�����ȼ���]
		 * ����InterfaceC�̳���InterfaceB������ʹ��InterfaceC��hello()��������� hello C
		 */
		demo2.hello();
	}
	
}
