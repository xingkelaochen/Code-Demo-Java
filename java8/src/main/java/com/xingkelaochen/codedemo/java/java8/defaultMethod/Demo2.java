package com.xingkelaochen.codedemo.java.java8.defaultMethod;

/**
 * 接口默认方法演示
 * 
 * <p>
 * 	继承一个实现InterfaceA的接口，并实现InterfaceB接口，InterfaceA与InterfaceB都申明了hello()的默认方法。
 *  解决<继承的类实现接口的默认方法，与实现接口的默认方法冲突，会选择具体哪个实现>的问题
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
		 * [父类的优先级高]
		 * ClassD所实现的InterfaceA与本类实现InterfaceB都申明了hello()默认方法，并且ClassD重写了此方法，使用父类ClassD中重写的方法，输出 hello D
		 */
		demo2.hello();
	}
	
}
