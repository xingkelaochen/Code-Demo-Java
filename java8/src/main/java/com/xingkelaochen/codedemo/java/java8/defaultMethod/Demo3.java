package com.xingkelaochen.codedemo.java.java8.defaultMethod;

/**
 * 接口默认方法演示
 * 
 * <p>
 * 	实现两个有相同签名的默认方法，并且InterfaceC继承InterfaceB重写了hello()方法，解决<实现多个接口，接口有继承重写时，会选择哪个具体实现>的问题
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
		 * [子接口更具体实现优先级高]
		 * 本例InterfaceC继承了InterfaceB，所以使用InterfaceC的hello()方法，输出 hello C
		 */
		demo2.hello();
	}
	
}
