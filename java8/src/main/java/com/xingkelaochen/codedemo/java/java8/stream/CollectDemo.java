package com.xingkelaochen.codedemo.java.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * 
 * Stream��collect�ռ�����<br />
 * collect����Ҳ�ǹ�Լ���������ڽ����е�Ԫ�ؽ��л���
 *
 * @author xingkelaochen
 * 
 *         <p>
 *         E-MAIL: admin@xingkelaochen.com <br />
 *         SITE: http://www.xingkelaochen.com
 *         </p>
 *
 */
public class CollectDemo {

	private static List<User> demoList = new ArrayList<>();
	static {
		demoList.add(new User("����", 20, "��"));
		demoList.add(new User("����", 30, "��"));
		demoList.add(new User("������", 40, "��"));
		demoList.add(new User("����", 20, "��"));
		demoList.add(new User("��ѩ", 18, "Ů"));
		demoList.add(new User("�º�", 25, "Ů"));
	}

	/**
	 * Collectors�����ṩ��һЩ��̬�Ĺ������������Խ��м���collect����<br />
	 * ������̬������java.util.stream.Collectors�����еľ�̬��������
	 */
	private void collect() {

		// ͳ���û��б����û�������
		long num = demoList.stream().collect(counting());
		// Ҳ����дΪ��ʵ����ʹ�õ�mapToLong(e -> 1L).sum()�ķ�ʽ
		demoList.stream().count();

		// �ҳ��������ֵ/��Сֵ maxBy/minBy��reversed�������ڽ�����˳��ת
		Comparator<User> comparator = Comparator.comparingInt(User::getAge).reversed();
		Optional<User> user = demoList.stream().collect(maxBy(comparator));
		user = demoList.stream().collect(minBy(comparator));
		// Ҳ����ʹ��Stream��sorted�����ٻ�ȡ��һ��Ԫ�صķ�ʽ
		user = demoList.stream().sorted(comparator).findFirst();

		// �ϼ��û�����
		Integer ageSum = demoList.stream().collect(summingInt(User::getAge));

		// �����û�ƽ������
		double ageAvg = demoList.stream().collect(averagingInt(User::getAge));

		// ��ȡ��ͳ��ֵ�ļ��ϣ����� count��sum��min��max��avg
		IntSummaryStatistics statistics = demoList.stream().collect(summarizingInt(User::getAge));

		// ƴ�������û�����
		String nameStr = demoList.stream().map(User::getName).collect(joining());
		nameStr = demoList.stream().map(User::getName).collect(joining(","));

		// ��������ʹ��Collectors��̬�����Ķ�ֻ��Ϊ�˷�������д��ʵ�����õĶ���redecing����
		// ����ʾ�����ܺͿ���д����������
		ageSum = demoList.stream().collect(reducing(0, User::getAge, (i, j) -> i + j));
		// reducing����������������ʼֵ��Function��
		// BinaryOperator������Function����ӳ�����е�Ԫ��Ϊ��Ҫͳ�ƻ��ܵ����ͣ�BinaryOperator��ͳ�ƻ��ܵĲ���

		// �����ȡ���������û�ʾ����û�г�ʼֵ��Functionת����������������Optional����
		user = demoList.stream().collect(reducing((i, j) -> i.getAge() > j.getAge() ? i : j));

		// ����
		// ���Ա����Լ򵥷���
		Map<String, List<User>> groupMap = demoList.stream().collect(groupingBy(User::getSex));
		// �Զ������ʵ��
		groupMap = demoList.stream().collect(groupingBy(u -> {
			if (u.getSex().equals("��")) {
				return "����";
			} else {
				return "Ů��";
			}
		}));
		// �༶���飬�����Ա�֣�������������Զ������
		Map<String, Map<String, List<User>>> muitGroupMap = demoList.stream()
				.collect(groupingBy(User::getSex, groupingBy(u -> {
					if (u.getAge() > 20) {
						return "A";
					} else {
						return "B";
					}
				})));
		// ����ͳ�Ƹ���
		Map<String, Long> groupCount = demoList.stream().collect(groupingBy(User::getSex, counting()));
		// ����ͳ��ÿ�� �Ա��������ġ���ΪmaxBy���ص���Optional���󣬵���ʵ��groupingBy�в������
		Map<String, Optional<User>> map = demoList.stream()
				.collect(groupingBy(User::getSex, maxBy(Comparator.comparing(User::getAge))));
		// collectingAndThen���ڽ��ռ����صĽ��ת��Ϊ��һ�����ͣ������ͽ����������õ�Optional����ֱ�ӵ�����get��������ת��
		Map<String, User> map2 = demoList.stream().collect(
				groupingBy(User::getSex, collectingAndThen(maxBy(Comparator.comparing(User::getAge)), Optional::get)));

		// ����
		// �����Ƿ����������ʽ������Predicateν�ʽ����ж�������Map<Boolean,List<T>>���͵Ķ���
		// ���Ƿ�����Ϊ���������û��б����
		Map<Boolean, List<User>> map3 = demoList.stream().collect(partitioningBy(u -> {
			if (u.getSex().equals("��")) {
				return true;
			} else {
				return false;
			}
		}));
		map3.get(true);
		// ���Ҫ��ȡ�����û��б����ϱߵķ�ʽ��ͬ���±�ʹ��filter�ķ�ʽ
		demoList.stream().filter(u -> u.getSex().equals("��")).collect(toList());
		// ���Ƿ����Է����������������
		Map<Boolean, Map<Integer, List<User>>> map4 = demoList.stream().collect(partitioningBy(u -> {
			if (u.getSex().equals("��")) {
				return true;
			} else {
				return false;
			}
		}, groupingBy(User::getAge)));
	}

	/**
	 * ʹ���Զ����Collectorʵ��
	 * ������ʾ��һ��������Collectos.toList()��̬����������Collectorʵ���࣬��Ȼ���߹�����ͬ�����������������
	 * 
	 * @see ToListCollector
	 * 
	 */
	public void collectorInterface() {

		User[] arr = demoList.toArray(new User[] {});

		List<User> userList = Arrays.stream(arr).collect(new ToListCollector<User>());

		System.out.println(userList);

		// ��ȻҲ���Բ���ʵ��Collector�Ľӿڣ�ֱ����collect�����ж���
		// ע�⣬���ַ�ʽ��combiner���ܴ����κε�Characteristics�Ĳ�����������Զ��һ��IDENTITY_FINISH��CONCURRENT������UNORDERED���ռ�����
		// ���ΪIDENTITY_FINISH��˵���ۼ������صĽ�������յĽ��������Ҫ�ٽ���ת���������Ե���������BiConsumer����ִ�У�
		Arrays.stream(arr).collect(ArrayList::new, List::add, (list1, list2) -> list1.addAll(list2));

	}

	/**
	 * Stream��reduce��collect�����Ƚ�
	 * 
	 */
	public void reduceOrCollect() {

		// ���û������ܼ�
		int sum = demoList.stream().map(User::getAge).reduce((i, j) -> i + j).orElse(0);
		sum = demoList.stream().collect(reducing(0, User::getAge, (i, j) -> i + j));
		sum = demoList.stream().collect(summingInt(User::getAge));
		sum = demoList.stream().mapToInt(User::getAge).sum();

		// ����ת��ΪList���
		List<User> userList = demoList.stream().reduce(new ArrayList<User>(), (List<User> l, User u) -> {
			l.add(u);
			return l;
		}, (List<User> l1, List<User> l2) -> {

			// ��collect����һ�����˴���combiner����ִ�У���Ϊ���ܴ����κε�Characteristics�Ĳ�����������Զ��һ��IDENTITY_FINISH��CONCURRENT������UNORDERED���ռ�����
			return null;
		});
		// �������󲻵ò�ʹ��reduce(List<User> identity, BiFunction<List<User>, ? super User,List<User>> accumulator, BinaryOperator<List<User>> combiner)����
		// ���ȹ�Լ�ĳ�ʼֵ��һ���յ�List<User>�б�
		// ��ÿ��Ԫ�ذ�����List<User>�б��з���
		// �ٽ�ǰһ��List��ֵ�ϲ������շ����ܵ�List
		// ��ʹ��collect�ͺܼ���
		userList = demoList.stream().collect(Collectors.toList());

		// ����ʽ����ṩ��һ������Ķ���ʵ�֣�Ӧ�þ�����ʹ�ü��ػ��ķ�ʽ������mapToInt(ToIntFunction).sum()
		// ��Ȼ�����ַ�ʽ�����������𣬱����ڲ��м����ϣ���ϸ��Collector���Զ���ʵ����characteristics()

	}

	public static void main(String[] args) {

		CollectDemo demo = new CollectDemo();

		demo.collectorInterface();
	}

}
