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
	 * ģ��Զ�̷���ķ���ӿڣ��ӿڽ���һ������Ϊ�ַ�����javascript������ʽ�����㲢���ؽ��
	 * @param str �ӿڲ���
	 * @return �������������ַ�������
	 */
	public int service(String str) throws Exception {
		
		// ����ģ����ʱ���̶�1��
		Thread.currentThread().sleep(1000);
		
		return Integer.parseInt(new ScriptEngineManager().getEngineByName("javascript").eval(str).toString());
		
	}
}
