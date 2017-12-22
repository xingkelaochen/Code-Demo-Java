package com.xingkelaochen.codedemo.java.java8.future;

import java.util.Random;

import javax.script.ScriptEngineManager;

/**
 * ģ��Զ�̷���
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
	 * ģ����Զ�̵����е���ʱ��Ϊ�˷�����ʾ�˴����ʱ��
	 * @throws InterruptedException
	 */
	private void delay() throws InterruptedException {
		Random random = new Random();
		int i = random.nextInt(5)+1;
		
		Thread.currentThread().sleep(i*1000);
	}
	
	/**
	 * ģ��Զ�̷���ķ���ӿڣ��ӿڽ���һ������Ϊ�ַ�����javascript������ʽ�����㲢���ؽ��
	 * @param str �ӿڲ���
	 * @return �������������ַ�������
	 */
	public int service(String str) throws Exception {
		
		// ����ģ����ʱ���̶�5��
		Thread.currentThread().sleep(5*1000);
		
		return Integer.parseInt(new ScriptEngineManager().getEngineByName("javascript").eval(str).toString());
		
	}
}
