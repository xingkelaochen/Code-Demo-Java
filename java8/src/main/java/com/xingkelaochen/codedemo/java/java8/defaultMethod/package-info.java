
/**
 * 
 * Java8接口默认方法
 * 
 * <p>
 * 	Java8中的接口允许使用default申明一个默认已实现的接口，实现类将继承默认方法，这类似于多重继承，将引起以下问题：
 * 	1. 实现多个接口，接口中有方法签名一致的默认方法，此时实现类该如何避免编译器无法选择？
 *  2. 继承的类实现接口的默认方法，与实现接口的默认方法冲突，会选择具体哪个实现？
 *  3. 实现多个接口，接口有继承重写时，会选择哪个具体实现？
 * </p>
 * 
 * <p>
 * 	本例使用三个接口、一个实现类来演示，用以说明上述三个问题的答案
 * </p>
 * 
 * @see com.xingkelaochen.codedemo.java.java8.defaultMethod.Demo1
 * @see com.xingkelaochen.codedemo.java.java8.defaultMethod.Demo2
 * @see com.xingkelaochen.codedemo.java.java8.defaultMethod.Demo3
 * 
 * @author xingkelaochen
 * 
 * <p>
 * E-MAIL: admin@xingkelaochen.com
 * <br />
 * SITE: http://www.xingkelaochen.com
 * </p>
 * @since 1.8
 *
 */
package com.xingkelaochen.codedemo.java.java8.defaultMethod;