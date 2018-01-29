package com.xingkelaochen.javademo.springcloud.feign.api.dto;

public class User {

	private String id;
	
	private String name;
	
	private String sex;
	
	private int age;
	
	public User() {
		
	}
	
	public User(String id,String name,String sex,int age) {
		
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
