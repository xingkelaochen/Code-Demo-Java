package com.xingkelaochen.codedemo.java.java8.behavior.filter;

/**
 * 
 * ��ʾ�龰ѧ����
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
	 * ѧ��
	 */
	private String studentId;
	
	/**
	 * ����
	 */
	private String name;
	
	/**
	 * ����
	 */
	private int age;
	
	/**
	 * �Ա�
	 */
	private String sex;
	
	/**
	 * �꼶
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
