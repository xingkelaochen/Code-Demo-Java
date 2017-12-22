package com.xingkelaochen.codedemo.java.java8.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * ��ʾFuture���÷�
 * 
 * <p>
 * ����ģ���Զ�̷�����{@link MockRemoteService}
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
	 * ģ���������Զ������������ս����ν����ƽ��ֵ
	 * <br />
	 * ��ͬ����ִ��ģʽ�£����÷������ȴ�֮ǰ�ķ��񷵻غ�ſ�ִ�С��ڱ����У�����Զ�̷�����ò���������ϵ���������ַ�ʽ��ʱ���
	 * @param a ��Ҫ�����a����
	 * @param b ��Ҫ�����b����
	 * @return ���ε��ý����ƽ��ֵ
	 */
	public int syncProcess(int a, int b) {

		try {

			long init = System.currentTimeMillis();
			long now = init;

			// �����������
			int result = service.service(a + "+" + b);
			System.out.println("��һ��ͬ�����÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"+ (System.currentTimeMillis() - now) + "����");

			// �����������
			now = System.currentTimeMillis();
			int result2 = service.service(a + "-" + b);
			System.out.println("�ڶ���ͬ�����÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"+ (System.currentTimeMillis() - now) + "����");

			// �����������
			now = System.currentTimeMillis();
			int result3 = service.service(a + "*" + b);
			System.out.println("������ͬ�����÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"+ (System.currentTimeMillis() - now) + "����");

			int sum = result+result2+result3;
			
			System.out.println("����ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");
			
			return sum/3;

		} catch (Exception ex) {
			ex.printStackTrace();

			return 0;
		}

	}
	
	/**
	 * ͬ������ִ��syncProcess�����ж���������������ͬ���������ڳ�����Ҫ�̵߳�������������ʹ��Future���ý���ĳʱ��������
	 * 
	 * @param a ��Ҫ�����a����
	 * @param b ��Ҫ�����b����
	 * @return ���ε��ý����ƽ��ֵ
	 */
	public int asyncProcess(int a,int b) {
		
		try {
			ExecutorService executoer = Executors.newCachedThreadPool();
			
			long init = System.currentTimeMillis();
			long now = init;
			
			Future<Integer> result = executoer.submit(new Callable<Integer>() {
	
				@Override
				public Integer call() throws Exception {
					// �����������
					int result = service.service(a + "+" + b);
					return result;
				}
			});
			
			System.out.println("��һ���첽���÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"+ (System.currentTimeMillis() - now) + "����");
			
			now = System.currentTimeMillis();
			Future<Integer> result2 = executoer.submit(new Callable<Integer>() {
	
				@Override
				public Integer call() throws Exception {
					// �����������
					int result = service.service(a + "-" + b);
					return result;
				}
			});
			
			System.out.println("�ڶ����첽���÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"+ (System.currentTimeMillis() - now) + "����");
			
			now = System.currentTimeMillis();
			Future<Integer> result3 = executoer.submit(new Callable<Integer>() {
	
				@Override
				public Integer call() throws Exception {
					// �����������
					int result = service.service(a + "*" + b);
					return result;
				}
			});
			
			System.out.println("�������첽���÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"+ (System.currentTimeMillis() - now) + "����");
			
			// ֱ���ˣ����η��������Ϊ�첽��ԭ�򣬻����޺�ʱ��ʹ��get�����ȴ����ء���
			
			int sum = result.get()+result2.get()+result3.get();
			
			System.out.println("����ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");
			
			// ���Կ�������Ϊ�̶���ʱ5���ԭ���첽����������5�����
			
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
