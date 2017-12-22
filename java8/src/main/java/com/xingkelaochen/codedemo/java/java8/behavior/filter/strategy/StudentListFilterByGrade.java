package com.xingkelaochen.codedemo.java.java8.behavior.filter.strategy;

import com.xingkelaochen.codedemo.java.java8.behavior.filter.Student;

/**
 * 
 * 判断学生年级的策略类型
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
public class StudentListFilterByGrade implements IListFilterStrategy<Student,Integer> {

	@Override
	public boolean test(Student t, Integer value) {
		
		return t.getGrade()==value;
	}

}
