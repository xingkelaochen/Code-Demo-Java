package com.xingkelaochen.codedemo.java.java8.defaultMethod;

/**
 * 
 * JAVA8�еĽӿ������ʹ��default�ؼ�������Ĭ�Ϸ���ʵ��
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
public interface InterfaceA {
	
	default void hello() {
		System.out.println("hello A");
	};
	
	default void methodA() {
		System.out.println("method A");
	}
}
