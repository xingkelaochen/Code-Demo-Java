package com.xingkelaochen.codedemo.java.java8.behavior.filter.strategy;

import java.util.Map;

import com.xingkelaochen.codedemo.java.java8.behavior.filter.Student;

/**
 * 以年级和性别为条件的筛选策略实现 <br />
 * 为了方便演示，两个筛选条件的值我们简单封装在Map对象中
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
public class StudentListFilterByGradeAndSex implements IListFilterStrategy<Student,Map<String,Object>> {

	@Override
	public boolean test(Student t, Map<String,Object> value) {
		
		if(t.getGrade()==Integer.parseInt(value.get("grade").toString())) {
			if(t.getSex().equals(value.get("sex").toString())) {
				return true;
			}
		}
		
		return false;
	}

}
