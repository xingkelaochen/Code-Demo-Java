package com.xingkelaochen.codedemo.java.java8.defaultMethod;

/**
 * �ӿ�Ĭ�Ϸ�����ʾ
 * 
 * <p>
 * 	�̳�һ��ʵ��InterfaceA�Ľӿڣ���ʵ��InterfaceB�ӿڣ�InterfaceA��InterfaceB��������hello()��Ĭ�Ϸ�����
 *  ���<�̳е���ʵ�ֽӿڵ�Ĭ�Ϸ�������ʵ�ֽӿڵ�Ĭ�Ϸ�����ͻ����ѡ������ĸ�ʵ��>������
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
public class Demo2 extends ClassD implements InterfaceB {

	public static void main(String[] args) {
		
		Demo2 demo2 = new Demo2();
		
		/**
		 * [��������ȼ���]
		 * ClassD��ʵ�ֵ�InterfaceA�뱾��ʵ��InterfaceB��������hello()Ĭ�Ϸ���������ClassD��д�˴˷�����ʹ�ø���ClassD����д�ķ�������� hello D
		 */
		demo2.hello();
	}
	
}
