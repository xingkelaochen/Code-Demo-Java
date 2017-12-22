package com.xingkelaochen.codedemo.java.java8.future;

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
	 * 模拟多次同步调用运算服务 <br />
	 * 在同步的执行模式下，调用服务必须等待之前的服务返回后才可执行
	 * 
	 * @return
	 */
	public String syncProcess(int a, int b) {

		try {

			long init = System.currentTimeMillis();
			long now = init;

			// 两个参数相加
			String result = service.service(a + "+" + b);
			System.out.println("第一次调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："+ (System.currentTimeMillis() - now) + "毫秒");

			// 结果再与第二个参数相减
			now = System.currentTimeMillis();
			result = service.service(result + "-" + b);
			System.out.println("第二次调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："+ (System.currentTimeMillis() - now) + "毫秒");

			// 结果再与第一个参数相乘
			now = System.currentTimeMillis();
			result = service.service(result + "*" + a);
			System.out.println("第三次调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："+ (System.currentTimeMillis() - now) + "毫秒");

			return result;

		} catch (Exception ex) {
			ex.printStackTrace();

			return "";
		}

	}
	
	public String asyncProcess(int a,int b) {
		
		
		return "";
	}

	public static void main(String[] args) {
		
		FutureDemo demo = new FutureDemo();

		String result = demo.syncProcess(1, 2);

		System.out.println("结果为：" + result);
	}
}
