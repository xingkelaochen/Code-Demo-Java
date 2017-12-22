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
 * ʹ��ɸѡѧ���б��龰����ʾ��Ϊ�������Ĺ����ݽ�
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
	 * ������Ҫͨ��ɸѡ��ȡ1�꼶��ѧ���б�
	 * 
	 * @param studentList
	 *            ѧ���б�
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
	 * ��Ϊ�꼶�ж�����������ǵ����������ã����Խ�ɸѡ���꼶��Ϊ����
	 * 
	 * @param studentList
	 *            ѧ���б�
	 * @param sex
	 *            �Ա����
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
	 * ��ɸѡ����������Ҫ����������Ϊ�ж����ݵĽ������������꼶�Ļ������������Ա��ж� <br />
	 * ���ƴ˷����Ĵ��뼫���ª������ά��������ı仯�͵���Ҫ��д��ͬ�Ĺ��˷�����Ӧ��Ѱ����õİ취��
	 * 
	 * @param studentList
	 *            ѧ���б�
	 * @param grade
	 *            �꼶
	 * @param sex
	 *            �Ա�
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
	 * Ϊ��ʹ���ǵĴ���������չ��ά��������ʹ�ò���ģʽ����ʵ�� <br />
	 * ɸѡҵ������Ӳ�Ӱ��˷�����ֻ��Ҫ�����Ӧ���Խӿڵ�ʵ�ּ��ɡ����÷����ε��ö���ӿ�ʵ�֣����ջ��ɸѡ�Ľ���<br />
	 * 
	 * @see StudentListFilterByGrade
	 * @see StudentListFilterBySex
	 * @see StudentListFilterByGradeAndSex
	 * 
	 * 
	 * @param studentList
	 *            ѧ���б�
	 * @param predicate
	 *            ɸѡʵ�ֶ���
	 * @param value
	 *            ɸѡ����ֵ
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
	 * ��ĿǰΪֹ������ʹ�ò���ģʽ�Ѿ��������ˣ������Ǻܱ��أ���Ϊ���ǲ��ò�Ϊÿ��ɸѡҵ��дһ��ʵ����<br />
	 * ʹ�������ڲ���ķ�ʽ���ƺ���ȥ�˽ӿ�ʵ����ı�д�������������ߵĴ��뼫���Ѻã������Ĵ��������˵���Ǹ����ѡ�
	 * 
	 * @param studentList
	 *            ѧ���б�
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

			}).test(student, "Ů")

			) {
				resultList.add(student);
			}

		}

		return resultList;

	}

	/**
	 * ����Jdk8����Ϊ�������ݣ�����ʹ��lambda��д������Ч���ּ����׶��Ĵ��� <br />
	 * ע��IListFilterPredicate�����ӿڣ���fitlerStrategy����ģʽ��IListFilterStrategy�ӿڲ�ͬ,�����ɸѡ�������ж�ֵ��ֱ����lambda���ʽ����
	 * 
	 * @param studentList
	 *            ѧ���б�
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
	 * �������ȶ�Ϊ�죬������JDK8�������ԣ��������ʹ��stream�����б��ɸѡ���ǽ���һ�����Գ�֮Ϊ��������� <br />
	 * filter������ʹ�õ�lambda����JDK8���Դ���Predicate����ʽ�ӿڵ�ǩ��
	 * 
	 * @param studentList
	 *            ѧ���б�
	 * @return List<Student>
	 */
	public List<Student> useStreamAndFilter(List<Student> studentList) {

		return studentList.stream().filter((x) -> x.getGrade() == 1).filter((x) -> x.getSex().equals("Ů"))
				.collect(Collectors.toList());

	}

	public static void main(String[] args) {

		StudentFilterDemo demo = new StudentFilterDemo();

		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student("20150001", "����", 7, "��", 1));
		studentList.add(new Student("20150002", "��÷÷", 8, "Ů", 1));
		studentList.add(new Student("20130012", "�ŷ�", 10, "��", 4));
		studentList.add(new Student("20120022", "����", 11, "Ů", 5));
		studentList.add(new Student("20110002", "��ѩ", 12, "Ů", 6));

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
