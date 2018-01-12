package com.xingkelaochen.codedemo.springboot.guide.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
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
@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private String id;
	
	private String name;
	
	private String sex;
	
	private int age;
	
	private boolean enabled;
	
	public User(String name,int age) {
		this.name = name;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
