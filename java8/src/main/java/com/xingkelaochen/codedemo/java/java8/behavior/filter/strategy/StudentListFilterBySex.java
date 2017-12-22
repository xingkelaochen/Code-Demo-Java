package com.xingkelaochen.codedemo.java.java8.behavior.filter.strategy;

import com.xingkelaochen.codedemo.java.java8.behavior.filter.Student;

/**
 * 
 * ���Ա�Ϊɸѡ����
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
public class StudentListFilterBySex implements IListFilterStrategy<Student,String> {

	@Override
	public boolean test(Student t, String value) {
		return t.getSex().equals(value);
	}

}
