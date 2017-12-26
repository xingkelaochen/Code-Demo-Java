package com.xingkelaochen.codedemo.java.java8.stream.base;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.xingkelaochen.codedemo.java.java8.stream.User;

/**
 * 
 * 演示Stream的基本用法
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
	 * 使用List<User>列表演示Stream的基本用法，可以与使用集合方式的实现进行对比
	 */
	public void demo() {
		
		List<User> userList = new ArrayList<>();
		userList.add(new User("张三",20,"男"));
		userList.add(new User("李四",30,"男"));
		userList.add(new User("王麻子",40,"男"));
		userList.add(new User("白雪",18,"女"));
		userList.add(new User("陈红",25,"女"));
		
		// 需求1：将用户列表中用户的信息拼接成【姓名|年龄|性别】的字符串，并返回List<String>列表
		List<String> strList = userList.stream().map(u->u.getName()+"|"+u.getAge()+"|"+u.getSex()).collect(Collectors.toList());
		strList.stream().forEach(user->System.out.println("User列表转换为字符串："+user));
		
		// 需求2：过滤出年龄在20岁以上的用户
		List<User> resultList = userList.stream().filter(u->u.getAge()>20).collect(Collectors.toList());
		resultList.stream().forEach(user->System.out.println("User列表过滤年龄20岁以上的用户："+user));
		
		// 需求3：按年龄排序，找出最年轻的用户
		userList.stream().sorted(Comparator.comparing(User::getAge)).limit(1).forEach(user->System.out.println("过滤最年轻的用户："+user));
		
		// 需求4：计算用户列表中所有用户年龄的合计
		int sum = userList.stream().map(user->user.getAge()).reduce(Integer::sum).get();
		System.out.println("列表中所有用户的年龄之和："+sum);
	}
	
	
	public static void main(String[] args) {
		
		StreamBaseDemo demo = new StreamBaseDemo();
		
		demo.demo();
		
	}
}
