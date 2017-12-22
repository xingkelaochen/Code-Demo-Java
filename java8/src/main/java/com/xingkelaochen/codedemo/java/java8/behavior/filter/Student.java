package com.xingkelaochen.codedemo.java.java8.behavior.filter;

/**
 * 
 * 演示情景学生类
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
public class Student {
	
	/**
	 * 学号
	 */
	private String studentId;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 年龄
	 */
	private int age;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 年级
	 */
	private int grade;

	public Student(String studentId, String name, int age, String sex, int grade) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.grade = grade;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	

}
