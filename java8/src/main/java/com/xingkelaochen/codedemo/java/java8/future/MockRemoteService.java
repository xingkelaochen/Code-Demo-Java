package com.xingkelaochen.codedemo.java.java8.future;

import java.util.Random;

import javax.script.ScriptEngineManager;

/**
 * 模拟远程服务
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
public class MockRemoteService {

	/**
	 * 模拟在远程调用中的延时，为了方便演示此处随机时间
	 * @throws InterruptedException
	 */
	private void delay() throws InterruptedException {
		Random random = new Random();
		int i = random.nextInt(5)+1;
		
		Thread.currentThread().sleep(i*1000);
	}
	
	/**
	 * 模拟远程服务的服务接口，接口接收一个类型为字符串的javascript运算表达式，计算并返回结果
	 * @param str 接口参数
	 * @return 返回运算结果的字符串类型
	 */
	public int service(String str) throws Exception {
		
		// 加入模拟延时，固定5秒
		Thread.currentThread().sleep(5*1000);
		
		return Integer.parseInt(new ScriptEngineManager().getEngineByName("javascript").eval(str).toString());
		
	}
}
