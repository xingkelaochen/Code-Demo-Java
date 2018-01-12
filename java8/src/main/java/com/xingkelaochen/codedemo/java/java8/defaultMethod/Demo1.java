package com.xingkelaochen.codedemo.java.java8.defaultMethod;

/**
 * 接口默认方法演示
 * 
 * <p>
 * 	实现两个有相同签名默认方法的接口，解决<实现多个接口，接口中有方法签名一致的默认方法，此时实现类该如何避免编译器无法选择>的问题
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
	 * 虽然理论上本类可以继承接口中定义的hello默认方法，但由于两个接口冲突，所以必须重写方法。菱形继承的问题也需要指定实现。
	 * 可以使用类似如下InterfaceA.super.hello()的方式指定使用哪个接口的默认实现。
	 */
	@Override
	public void hello() {
		InterfaceA.super.hello();
	}
	
	public static void main(String[] args) {
		
		Demo1 demo1 = new Demo1();
		/**
		 * [本类的优先级最高]
		 * 按照指定的实现，使用InterfaceA的hello()方法，输出 hello A
		 */
		demo1.hello();
	}
}
