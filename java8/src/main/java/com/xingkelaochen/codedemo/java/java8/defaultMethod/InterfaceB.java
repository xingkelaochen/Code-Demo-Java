package com.xingkelaochen.codedemo.java.java8.defaultMethod;

/**
 *  JAVA8�еĽӿ������ʹ��default�ؼ�������Ĭ�Ϸ���ʵ��
 *
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
public interface InterfaceB {

	default void hello() {
		System.out.println("hello B");
	}
	
	default void methodB() {
		System.out.println("method B");
	}
}
