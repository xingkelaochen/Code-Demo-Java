package com.xingkelaochen.codedemo.java.java8.future;

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
	 * ģ����ͬ������������� <br />
	 * ��ͬ����ִ��ģʽ�£����÷������ȴ�֮ǰ�ķ��񷵻غ�ſ�ִ��
	 * 
	 * @return
	 */
	public String syncProcess(int a, int b) {

		try {

			long init = System.currentTimeMillis();
			long now = init;

			// �����������
			String result = service.service(a + "+" + b);
			System.out.println("��һ�ε��÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"+ (System.currentTimeMillis() - now) + "����");

			// �������ڶ����������
			now = System.currentTimeMillis();
			result = service.service(result + "-" + b);
			System.out.println("�ڶ��ε��÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"+ (System.currentTimeMillis() - now) + "����");

			// ��������һ���������
			now = System.currentTimeMillis();
			result = service.service(result + "*" + a);
			System.out.println("�����ε��÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"+ (System.currentTimeMillis() - now) + "����");

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

		System.out.println("���Ϊ��" + result);
	}
}
