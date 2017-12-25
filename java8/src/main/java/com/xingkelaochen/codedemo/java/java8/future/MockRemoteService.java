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
	 * 模拟远程服务的服务接口，接口接收一个类型为字符串的javascript运算表达式，计算并返回结果
	 * @param str 接口参数
	 * @return 返回运算结果的字符串类型
	 */
	public int service(String str) throws Exception {
		
		// 加入模拟延时，固定1秒
		Thread.currentThread().sleep(1000);
		
		return Integer.parseInt(new ScriptEngineManager().getEngineByName("javascript").eval(str).toString());
		
	}
}
