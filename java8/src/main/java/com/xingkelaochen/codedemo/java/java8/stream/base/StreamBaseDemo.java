package com.xingkelaochen.codedemo.java.java8.stream.base;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.xingkelaochen.codedemo.java.java8.stream.User;

/**
 * 
 * ��ʾStream�Ļ����÷�
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
public class StreamBaseDemo {

	/**
	 * ʹ��List<User>�б���ʾStream�Ļ����÷���������ʹ�ü��Ϸ�ʽ��ʵ�ֽ��жԱ�
	 */
	public void demo() {
		
		List<User> userList = new ArrayList<>();
		userList.add(new User("����",20,"��"));
		userList.add(new User("����",30,"��"));
		userList.add(new User("������",40,"��"));
		userList.add(new User("��ѩ",18,"Ů"));
		userList.add(new User("�º�",25,"Ů"));
		
		// ����1�����û��б����û�����Ϣƴ�ӳɡ�����|����|�Ա𡿵��ַ�����������List<String>�б�
		List<String> strList = userList.stream().map(u->u.getName()+"|"+u.getAge()+"|"+u.getSex()).collect(Collectors.toList());
		strList.stream().forEach(user->System.out.println("User�б�ת��Ϊ�ַ�����"+user));
		
		// ����2�����˳�������20�����ϵ��û�
		List<User> resultList = userList.stream().filter(u->u.getAge()>20).collect(Collectors.toList());
		resultList.stream().forEach(user->System.out.println("User�б��������20�����ϵ��û���"+user));
		
		// ����3�������������ҳ���������û�
		userList.stream().sorted(Comparator.comparing(User::getAge)).limit(1).forEach(user->System.out.println("������������û���"+user));
		
		// ����4�������û��б��������û�����ĺϼ�
		int sum = userList.stream().map(user->user.getAge()).reduce(Integer::sum).get();
		System.out.println("�б��������û�������֮�ͣ�"+sum);
	}
	
	
	public static void main(String[] args) {
		
		StreamBaseDemo demo = new StreamBaseDemo();
		
		demo.demo();
		
	}
}
