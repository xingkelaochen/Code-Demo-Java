package com.xingkelaochen.codedemo.java.java8.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * 演示Future的用法
 * 
 * <p>
 * 调用模拟的远程服务存根{@link MockRemoteService}
 * </p>
 *
 * @author xingkelaochen
 * 
 *         <p>
 *         E-MAIL: admin@xingkelaochen.com <br />
 *         SITE: http://www.xingkelaochen.com
 *         </p>
 *
 */
public class FutureDemo {

	private MockRemoteService service = new MockRemoteService();

	/**
	 * 模拟调用三次远程运算服务，最终将三次结果求平均值
	 * <br />
	 * 在同步的执行模式下，调用服务必须等待之前的服务返回后才可执行。在本例中，三次远程服务调用并无依赖关系，所以这种方式耗时最长。
	 * @param a 需要运算的a整数
	 * @param b 需要运算的b整数
	 * @return 三次调用结果的平均值
	 */
	public int syncProcess(int a, int b) {

		try {

			long init = System.currentTimeMillis();
			long now = init;

			// 两个参数相加
			int result = service.service(a + "+" + b);
			System.out.println("第一次同步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："+ (System.currentTimeMillis() - now) + "毫秒");

			// 两个参数相减
			now = System.currentTimeMillis();
			int result2 = service.service(a + "-" + b);
			System.out.println("第二次同步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："+ (System.currentTimeMillis() - now) + "毫秒");

			// 两个参数相乘
			now = System.currentTimeMillis();
			int result3 = service.service(a + "*" + b);
			System.out.println("第三次同步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："+ (System.currentTimeMillis() - now) + "毫秒");

			int sum = result+result2+result3;
			
			System.out.println("方法执行总耗时：" + (System.currentTimeMillis() - init) + "毫秒");
			
			return sum/3;

		} catch (Exception ex) {
			ex.printStackTrace();

			return 0;
		}

	}
	
	/**
	 * 同样还是执行syncProcess方法中对于两个参数的相同操作，由于程序需要线程的运算结果，所以使用Future引用将来某时的运算结果
	 * 
	 * @param a 需要运算的a整数
	 * @param b 需要运算的b整数
	 * @return 三次调用结果的平均值
	 */
	public int asyncProcess(int a,int b) {
		
		try {
			ExecutorService executoer = Executors.newCachedThreadPool();
			
			long init = System.currentTimeMillis();
			long now = init;
			
			Future<Integer> result = executoer.submit(new Callable<Integer>() {
	
				@Override
				public Integer call() throws Exception {
					// 两个参数相加
					int result = service.service(a + "+" + b);
					return result;
				}
			});
			
			System.out.println("第一次异步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："+ (System.currentTimeMillis() - now) + "毫秒");
			
			now = System.currentTimeMillis();
			Future<Integer> result2 = executoer.submit(new Callable<Integer>() {
	
				@Override
				public Integer call() throws Exception {
					// 两个参数相减
					int result = service.service(a + "-" + b);
					return result;
				}
			});
			
			System.out.println("第二次异步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："+ (System.currentTimeMillis() - now) + "毫秒");
			
			now = System.currentTimeMillis();
			Future<Integer> result3 = executoer.submit(new Callable<Integer>() {
	
				@Override
				public Integer call() throws Exception {
					// 两个参数相乘
					int result = service.service(a + "*" + b);
					return result;
				}
			});
			
			System.out.println("第三次异步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："+ (System.currentTimeMillis() - now) + "毫秒");
			
			// 直到此，三次服务调用因为异步的原因，基本无耗时，使用get方法等待返回……
			
			int sum = result.get()+result2.get()+result3.get();
			
			System.out.println("方法执行总耗时：" + (System.currentTimeMillis() - init) + "毫秒");
			
			// 可以看到，因为固定延时5秒的原因，异步方法基本在5秒完成
			
			return sum/3;
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static void main(String[] args) {
		
		FutureDemo demo = new FutureDemo();

		int result = demo.syncProcess(4, 5);
		
		result = demo.asyncProcess(4, 5);

	}
}
