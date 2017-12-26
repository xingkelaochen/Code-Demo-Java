package com.xingkelaochen.codedemo.java.java8.stream;

/**
 * 
 * 演示Stream操作的用户对象
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
public class User {

	/**
	 * 用户姓名
	 */
	private String name;
	
	/**
	 * 用户年龄
	 */
	private int age;
	
	/**
	 * 用户性别
	 */
	private String sex;
	
	public User(String name,int age,String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("'name':'").append(name).append("','age':").append(age).append(",'sex':'").append(sex).append("'");
		
		return sb.toString();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
}
