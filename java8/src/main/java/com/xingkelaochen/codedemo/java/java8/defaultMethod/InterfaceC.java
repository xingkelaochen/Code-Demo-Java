package com.xingkelaochen.codedemo.java.java8.defaultMethod;

/**
 * ����ӿ�C����дInterfaceB��Ĭ�Ϸ���hello()
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
public interface InterfaceC extends InterfaceB {

	@Override
	default void hello() {
		System.out.println("hello C");
	}

}
