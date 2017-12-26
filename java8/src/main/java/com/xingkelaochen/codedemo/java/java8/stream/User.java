package com.xingkelaochen.codedemo.java.java8.stream;

/**
 * 
 * ��ʾStream�������û�����
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
	 * �û�����
	 */
	private String name;
	
	/**
	 * �û�����
	 */
	private int age;
	
	/**
	 * �û��Ա�
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
