package com.xingkelaochen.codedemo.java.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 
 * ��ʾStream�Ļ����÷�
 * 
 *
 * @author xingkelaochen
 * 
 *         <p>
 *         E-MAIL: admin@xingkelaochen.com <br />
 *         SITE: http://www.xingkelaochen.com
 *         </p>
 *
 */
public class StreamBaseDemo {

	private static List<User> demoList = new ArrayList<>();
	static {
		demoList.add(new User("����", 20, "��"));
		demoList.add(new User("����", 30, "��"));
		demoList.add(new User("������", 40, "��"));
		demoList.add(new User("��ѩ", 18, "Ů"));
		demoList.add(new User("�º�", 25, "Ů"));
	}

	/**
	 * ��ʾStream�ļ��÷�
	 */
	public void demo() {

		// ����1�����û��б����û�����Ϣƴ�ӳɡ�����|����|�Ա𡿵��ַ�����������List<String>�б�
		List<String> strList = demoList.stream().map(u -> u.getName() + "|" + u.getAge() + "|" + u.getSex())
				.collect(Collectors.toList());
		strList.stream().forEach(user -> System.out.println("User�б�ת��Ϊ�ַ�����" + user));

		// ����2��ɸѡ��������20�����ϵ��û�
		List<User> resultList = demoList.stream().filter(u -> u.getAge() > 20).collect(Collectors.toList());
		resultList.stream().forEach(user -> System.out.println("User�б�ɸѡ����20�����ϵ��û���" + user));

		// ����3�������������ҳ���������û�
		demoList.stream().sorted(Comparator.comparing(User::getAge)).limit(1)
				.forEach(user -> System.out.println("ɸѡ��������û���" + user));

		// ����4�������û��б��������û�����ĺϼ�
		int sum = demoList.stream().map(user -> user.getAge()).reduce(Integer::sum).get();
		System.out.println("�б��������û�������֮�ͣ�" + sum);
	}

	// ������ʾStream�ĳ����÷�
	
	/**
	 * Stream��ɸѡ
	 */
	public void filter() {

		// ɸѡ�û��б�����������20�����ϵ������û�
		// filter����һ��Predicate��ν�ʲ���
		List<User> userList = demoList.stream().filter(user -> user.getAge() > 20)
				.filter(user -> user.getSex().equals("��")).collect(Collectors.toList());

		userList.stream().forEach(user -> System.out.println("ɸѡ�û��б�����������20�����ϵ������û���" + user));

	}

	/**
	 * Stream�ض�Ԫ��
	 */
	public void limit() {

		// limit����һ��long���������ض����е�Ԫ��(�����б��е�˳��)
		demoList.stream().limit(2).forEach(user -> System.out.println("�б���ǰ�����û���" + user));

	}

	/**
	 * Stream�������е�Ԫ��
	 */
	public void skip() {

		// skip����һ��long����������������˳������ָ����ֵ��Ԫ��
		demoList.stream().skip(2).forEach(user -> System.out.println("�����б���ǰ�����û���" + user));
	}

	/**
	 * Streamӳ��
	 */
	public void map() {

		// ��ȡ�û��б��������û��������ַ����б�
		List<String> nameList = demoList.stream().map(User::getName).collect(Collectors.toList());
		nameList.stream().forEach(System.out::println);

		// ��������б��в��ظ��ĵ�������
		nameList.stream().map(s -> s.split("")).distinct().forEach(System.out::println);
		// �ϱߵķ�ʽ�в�ͨ����Ϊsplit��ÿ�����ƶ�ӳ�����һ��String[]����
		// Arrays.stream�������Խ�һ��String[]����ת��ΪStream<String>������
		nameList.stream().map(s -> s.split("")).map(Arrays::stream).distinct().forEach(System.out::println);
		// �ϱߵķ�ʽ�����в�ͨ��Ŀǰ��ӳ��Ļ���Stream<String>������Ϊmap(Arrays::stream)��ÿ�����鶼ӳ����˶�������(һ����5�����飬����5����������)
		// flatMap���Խ���ǰ����ʹ��map(Arrays::stream)���ɵĶ������ƽ��Ϊһ������������а���ÿ��������������
		nameList.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::println);

		// ���Ա���飬����û���ϵ�����
		List<User> manList = demoList.stream().filter(user -> user.getSex().equals("��")).collect(Collectors.toList());
		List<User> womanList = demoList.stream().filter(user -> user.getSex().equals("Ů")).collect(Collectors.toList());
		manList.stream().flatMap(man -> womanList.stream().map(woman -> new User[] { man, woman }))
				.forEach(arr -> System.out.println(arr[0].getName() + "_" + arr[1].getName()));
		// �൱���������û��б��ѭ���У�ִ��Ů���û��б����ѭ����ƴ�ӳ���Ӧ�ַ��������

	}

	/**
	 * Stream��ƥ��Ͳ���
	 */
	public void matchAndFind() {

		// anyMatch��������Ƿ��з��ϲ���Predicate��Ԫ�أ�����boolean���ն˲���
		if (demoList.stream().anyMatch(user -> user.getAge() >= 40)) {
			System.out.println("�û��б��д���40��(����)���ϵ��û�");
		}

		// allMatch��������Ƿ�ȫ�����ϲ���Predicate��Ԫ�أ�����boolean���ն˲���
		if (demoList.stream().allMatch(user -> user.getSex().equals("��"))) {
			System.out.println("�û��б��ж������Ե��û�");
		} else {
			System.out.println("�û��б��в�ȫ�����Ե��û�");
		}

		// ��allMatch�෴��noneMatch�������Ԫ��ȫ��������Predicate����
		if (demoList.stream().noneMatch(user -> user.getAge() > 50)) {
			System.out.println("�û��б���û�д���50����û�");
		}

		// ���ϵ�anyMatch��allMatch��noneMatch���Ƕ�·��������Ϊ������Ҫ������������Ԫ�ؾ��ܵõ����

		// ɸѡ�������/����30����û��������ط�������������һ��
		// findAny�᷵�ص�ǰ���е�����һ����������Optional����ifPresent������������ڶ�����ִ�����Consumer
		demoList.stream().filter(user -> user.getAge() >= 30).findAny().ifPresent(System.out::println);

		// Optional<User>���������ֵ����ʹ��orElse����һ���µĶ��󷵻�
		System.out.println(demoList.stream().filter(user -> user.getAge() > 60).findAny().orElse(new User("����ͷ", 75, "��")));

		// findFirst���ڷ������е�һ��Ԫ��
		System.out.println(demoList.stream().findFirst().get());
		// findAnyҲ����ͬ�����ã���findFirst������ڲ������еĻ�����̫��
	}
	
	/**
	 * ���Stream�����������
	 */
	public void comparing() {
		
		// ʹ��Stream��sorted��������Ƚ������󣬰����û�����������򣬲������������б�
		demoList.stream().map(User::getAge).sorted(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1<o2) {
					return -1;
				}else if(o1>o2) {
					return 1;
				}else {
					return 0;
				}
			}
		}).collect(Collectors.toList());
		
		/**
		 * ������Comparator�ṩ���¹�������comparing�����ܸ���һ��
		 */
		demoList.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
		
		/**
		 * Comparator��reversed���������ǽ����������������
		 */
		demoList.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
		
	}

	/**
	 * Reduce��Լ���ն˲��������������������ս��
	 * <br />
	 * Reduce����һ��BinaryOperator������������������(T-T)->T
	 */
	public void reduce() {

		// ���û��б��������û��������ܺ�
		demoList.stream().map(User::getAge).reduce(0, (u1, u2) -> u1 + u2);
		// ���������Ƚ��û���ӳ���Ϊ�����Stream<Integer>������ʹ��reduce��������ĺϼƲ�����reduce��һ�������ǳ�ʼֵ���ڶ���������
		// BinaryOperator<Integer>,������ǰ������Ԫ�ؽ����ۼӡ�
		demoList.stream().map(User::getAge).reduce((u1, u2) -> u1 + u2).get();
		// ��������û�г�ʼֵ��������Ϊ��������û���κ����ݣ�����û��Ϊ�����ó�ʼֵ�����������ص���һ��Optional<Integer>
		// ���ܸ��򵥣�sum�������ڽ����е�intԪ�ؽ����ۼӣ�֮���Ի���Ҫʹ��mapToInt����ΪStream<Integer>����֧��sum������������Ҫ�����ػ�ΪIntStream�����ͬ��reduce(0, Integer::sum)
		demoList.stream().mapToInt(User::getAge).sum();
		// �ػ���Ҳ����ʹ��boxed������Ԫ�ؽ���װ��
		Stream<Integer> stream = IntStream.rangeClosed(1, 3).boxed();

		// ���ֵ����Сֵ
		demoList.stream().map(user -> user.getAge()).reduce(Integer::max);
		demoList.stream().map(user -> user.getAge()).reduce(Integer::min);
		
		// Ԫ������
		demoList.stream().count();
		
		// ����
		Map<String,List<User>> map = demoList.stream().collect(Collectors.groupingBy(User::getSex));
	}
	
	/**
	 * �������ķ���
	 */
	public void createStream() {
		
		// ʹ��of��������һ��Stream<User>������
		Stream<User> userStream = Stream.of(demoList.toArray(new User[] {}));
		
		// ��������
		Stream<User> emptyStream = Stream.empty();
		
		// �����鹹����
		Stream<String> stringStream = Arrays.stream(new String[] {"a","b","c"});
		
		try {
			// ���ļ���ȡStream��lines�������е�Ԫ�����ļ���ÿһ�е��ַ���
			Files.lines(Paths.get("")).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ʹ��iterate����������
		Stream.iterate(0, n->n+1).limit(20).forEach(System.out::println);
		Stream.iterate(new int[] {0,1}, n->new int[] {n[1],n[0]+n[1]}).limit(20).forEach(System.out::println);
		
		// generateҲͬ������������������iterate��ͬ��������һ��Supplier������������Ԫ��
		IntStream intStream = IntStream.generate(()->1);
	}

	public static void main(String[] args) {

		StreamBaseDemo demo = new StreamBaseDemo();

		demo.demo();

		demo.filter();

		demo.limit();

		demo.skip();

		demo.map();

		demo.matchAndFind();
	}
}
