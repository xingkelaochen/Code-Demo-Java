/**
 * 演示自Jdk1.5加入的Future，以及Jdk1.8新引入的CompletableFuture的用法及其意义
 * 
 * <p>
 * Future表示异步计算的结果，它为将来某个时刻要进行的计算进行建模，使用get方法获取计算的结果。<br />
 * 使用Future开启新线程进行运算任务，并建模返回结果引用后，可以继续进行其他重要的运算操作，直到调用get方法前主线程都不会被阻塞。<br />
 * 使用的意义是，如果一个任务包含多步运算时间未知的操作，并且任务不以前置任务的结果为输入的情况下，使用Future能有效提升程序的并行效率。
 * </p>
 *
 * <p>
 * 本例将模拟一个需要请求多个远程服务的应用场景，来演示说明。
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
package com.xingkelaochen.codedemo.java.java8.future;