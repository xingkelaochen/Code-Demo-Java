package com.xingkelaochen.codedemo.java.java8.behavior.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.xingkelaochen.codedemo.java.java8.behavior.filter.strategy.IListFilterStrategy;
import com.xingkelaochen.codedemo.java.java8.behavior.filter.strategy.StudentListFilterByGrade;
import com.xingkelaochen.codedemo.java.java8.behavior.filter.strategy.StudentListFilterByGradeAndSex;
import com.xingkelaochen.codedemo.java.java8.behavior.filter.strategy.StudentListFilterBySex;

/**
 * 
 * 使用筛选学生列表情景，演示行为参数化的功能演进
 *
 * @author xingkelaochen
 * 
 *         <p>
 *         E-MAIL: admin@xingkelaochen.com <br />
 *         SITE: http://www.xingkelaochen.com
 *         </p>
 *
 */
public class StudentFilterDemo {

	/**
	 * 假设需要通过筛选获取1年级的学生列表
	 * 
	 * @param studentList
	 *            学生列表
	 * @return List<Student>
	 */
	public List<Student> ancientFitlerGrade1(List<Student> studentList) {

		List<Student> resultList = new ArrayList<>();

		for (Student student : studentList) {
			if (student.getGrade() == 1) {
				resultList.add(student);
			}
		}

		return resultList;

	}

	/**
	 * 因为年级有多种情况，考虑到方法的重用，所以将筛选的年级做为参数
	 * 
	 * @param studentList
	 *            学生列表
	 * @param sex
	 *            性别参数
	 * @return List<Student>
	 */
	public List<Student> ancientFitlerBySex(List<Student> studentList, int grade) {

		List<Student> resultList = new ArrayList<>();

		for (Student student : studentList) {
			if (student.getGrade() == grade) {
				resultList.add(student);
			}
		}

		return resultList;

	}

	/**
	 * 但筛选条件可能需要以其他属性为判断依据的交集，比如在年级的基本上再增加性别判断 <br />
	 * 类似此方法的代码极其丑陋且难以维护，需求的变化就导致要编写不同的过滤方法，应该寻求更好的办法。
	 * 
	 * @param studentList
	 *            学生列表
	 * @param grade
	 *            年级
	 * @param sex
	 *            性别
	 * @return List<Student>
	 */
	public List<Student> ancientFitlerGirlAndGrade(List<Student> studentList, int grade, String sex) {
		List<Student> resultList = new ArrayList<>();

		for (Student student : studentList) {
			if (student.getGrade() == grade) {
				if (student.getSex().equals(sex)) {
					resultList.add(student);
				}
			}
		}

		return resultList;
	}

	/**
	 * 
	 * 为了使我们的代码容易扩展与维护，这里使用策略模式进行实现 <br />
	 * 筛选业务的增加不影响此方法，只需要添加相应策略接口的实现即可。调用方依次调用多个接口实现，最终获得筛选的交集<br />
	 * 
	 * @see StudentListFilterByGrade
	 * @see StudentListFilterBySex
	 * @see StudentListFilterByGradeAndSex
	 * 
	 * 
	 * @param studentList
	 *            学生列表
	 * @param predicate
	 *            筛选实现对象
	 * @param value
	 *            筛选条件值
	 * @return List<Student>
	 */
	public <P> List<Student> fitlerStrategy(List<Student> studentList, IListFilterStrategy<Student, P> strategy,
			P value) {

		List<Student> resultList = new ArrayList<>();

		for (Student student : studentList) {
			if (strategy.test(student, value)) {
				resultList.add(student);
			}
		}

		return resultList;
	}

	/**
	 * 到目前为止，好像使用策略模式已经很完美了，但还是很笨重，因为我们不得不为每个筛选业务写一个实现类<br />
	 * 使用匿名内部类的方式，似乎免去了接口实现类的编写工作，但调用者的代码极不友好，这样的代码对人来说真是个灾难。
	 * 
	 * @param studentList
	 *            学生列表
	 * @return
	 */
	public List<Student> byAnonymousInnerClass(List<Student> studentList) {

		List<Student> resultList = new ArrayList<>();

		for (Student student : studentList) {

			if ((new IListFilterStrategy<Student, Integer>() {

				@Override
				public boolean test(Student t, Integer value) {

					return t.getGrade() == value;
				}

			}).test(student, 1) && (new IListFilterStrategy<Student, String>() {

				@Override
				public boolean test(Student t, String value) {

					return t.getSex().equals(value);
				}

			}).test(student, "女")

			) {
				resultList.add(student);
			}

		}

		return resultList;

	}

	/**
	 * 基于Jdk8的行为参数传递，我们使用lambda能写出极富效率又极其易读的代码 <br />
	 * 注意IListFilterPredicate函数接口，与fitlerStrategy策略模式中IListFilterStrategy接口不同,这里的筛选条件的判定值将直接由lambda表达式传递
	 * 
	 * @param studentList
	 *            学生列表
	 * @return studentList
	 */
	private <P> List<Student> filterStudentList(List<Student> studentList, IListFilterPredicate<Student> predicate) {

		List<Student> resultList = new ArrayList<>();

		for (Student student : studentList) {
			if (predicate.test(student)) {
				resultList.add(student);
			}
		}
		return resultList;
	}

	public List<Student> useBehavioralParameterization(List<Student> studentList) {

		return filterStudentList(studentList, (x) -> x.getGrade() == 4);
	}

	/**
	 * 让我们先睹为快，利益于JDK8的新特性，如果我们使用stream来做列表的筛选，那将是一件可以称之为神奇的事情 <br />
	 * filter方法中使用的lambda符合JDK8中自带的Predicate函数式接口的签名
	 * 
	 * @param studentList
	 *            学生列表
	 * @return List<Student>
	 */
	public List<Student> useStreamAndFilter(List<Student> studentList) {

		return studentList.stream().filter((x) -> x.getGrade() == 1).filter((x) -> x.getSex().equals("女"))
				.collect(Collectors.toList());

	}

	public static void main(String[] args) {

		StudentFilterDemo demo = new StudentFilterDemo();

		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student("20150001", "李雷", 7, "男", 1));
		studentList.add(new Student("20150002", "韩梅梅", 8, "女", 1));
		studentList.add(new Student("20130012", "张峰", 10, "男", 4));
		studentList.add(new Student("20120022", "王婷", 11, "女", 5));
		studentList.add(new Student("20110002", "赵雪", 12, "女", 6));

		List<Student> resultList = demo.useBehavioralParameterization(studentList);

		for (Student student : resultList) {
			System.out.println(student.getStudentId());
		}

		// to use stream
		resultList = demo.useStreamAndFilter(studentList);
		for (Student student : resultList) {
			System.out.println(student.getStudentId());
		}
	}
}
