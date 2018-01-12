package com.xingkelaochen.codedemo.java.java8.defaultMethod;

/**
 *  JAVA8中的接口类可以使用default关键字申明默认方法实现
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
