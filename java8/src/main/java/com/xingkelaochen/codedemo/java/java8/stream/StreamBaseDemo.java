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
 * 演示Stream的基本用法
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
		demoList.add(new User("张三", 20, "男"));
		demoList.add(new User("李四", 30, "男"));
		demoList.add(new User("王麻子", 40, "男"));
		demoList.add(new User("白雪", 18, "女"));
		demoList.add(new User("陈红", 25, "女"));
	}

	/**
	 * 演示Stream的简单用法
	 */
	public void demo() {

		// 需求1：将用户列表中用户的信息拼接成【姓名|年龄|性别】的字符串，并返回List<String>列表
		List<String> strList = demoList.stream().map(u -> u.getName() + "|" + u.getAge() + "|" + u.getSex())
				.collect(Collectors.toList());
		strList.stream().forEach(user -> System.out.println("User列表转换为字符串：" + user));

		// 需求2：筛选出年龄在20岁以上的用户
		List<User> resultList = demoList.stream().filter(u -> u.getAge() > 20).collect(Collectors.toList());
		resultList.stream().forEach(user -> System.out.println("User列表筛选年龄20岁以上的用户：" + user));

		// 需求3：按年龄排序，找出最年轻的用户
		demoList.stream().sorted(Comparator.comparing(User::getAge)).limit(1)
				.forEach(user -> System.out.println("筛选最年轻的用户：" + user));

		// 需求4：计算用户列表中所有用户年龄的合计
		int sum = demoList.stream().map(user -> user.getAge()).reduce(Integer::sum).get();
		System.out.println("列表中所有用户的年龄之和：" + sum);
	}

	// 以下演示Stream的常用用法
	
	/**
	 * Stream的筛选
	 */
	public void filter() {

		// 筛选用户列表中所有年龄20岁以上的男性用户
		// filter接受一个Predicate的谓词参数
		List<User> userList = demoList.stream().filter(user -> user.getAge() > 20)
				.filter(user -> user.getSex().equals("男")).collect(Collectors.toList());

		userList.stream().forEach(user -> System.out.println("筛选用户列表中所有年龄20岁以上的男性用户：" + user));

	}

	/**
	 * Stream截断元素
	 */
	public void limit() {

		// limit接受一个long参数，将截断流中的元素(按照列表中的顺序)
		demoList.stream().limit(2).forEach(user -> System.out.println("列表中前两个用户：" + user));

	}

	/**
	 * Stream跳过流中的元素
	 */
	public void skip() {

		// skip接受一个long参数，将按照流的顺序跳过指定数值的元素
		demoList.stream().skip(2).forEach(user -> System.out.println("跳过列表中前两个用户：" + user));
	}

	/**
	 * Stream映射
	 */
	public void map() {

		// 获取用户列表中所有用户的姓名字符串列表
		List<String> nameList = demoList.stream().map(User::getName).collect(Collectors.toList());
		nameList.stream().forEach(System.out::println);

		// 输出名字列表中不重复的单个汉字
		nameList.stream().map(s -> s.split("")).distinct().forEach(System.out::println);
		// 上边的方式行不通，因为split把每个名称都映射成了一个String[]的流
		// Arrays.stream方法可以将一个String[]对象转换为Stream<String>流对象
		nameList.stream().map(s -> s.split("")).map(Arrays::stream).distinct().forEach(System.out::println);
		// 上边的方式还是行不通，目前流映射的还是Stream<String>对象，因为map(Arrays::stream)把每个数组都映射成了独立的流(一共有5个数组，生成5个独立的流)
		// flatMap可以将当前所有使用map(Arrays::stream)生成的多个流扁平化为一个流，这个流中包含每个数组流的内容
		nameList.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::println);

		// 按性别分组，输出用户组合的名字
		List<User> manList = demoList.stream().filter(user -> user.getSex().equals("男")).collect(Collectors.toList());
		List<User> womanList = demoList.stream().filter(user -> user.getSex().equals("女")).collect(Collectors.toList());
		manList.stream().flatMap(man -> womanList.stream().map(woman -> new User[] { man, woman }))
				.forEach(arr -> System.out.println(arr[0].getName() + "_" + arr[1].getName()));
		// 相当于在男性用户列表的循环中，执行女性用户列表的子循环，拼接成相应字符串输出。

	}

	/**
	 * Stream的匹配和查找
	 */
	public void matchAndFind() {

		// anyMatch检测流中是否有符合参数Predicate的元素，返回boolean是终端操作
		if (demoList.stream().anyMatch(user -> user.getAge() >= 40)) {
			System.out.println("用户列表中存在40岁(包含)以上的用户");
		}

		// allMatch检测流中是否全部符合参数Predicate的元素，返回boolean是终端操作
		if (demoList.stream().allMatch(user -> user.getSex().equals("男"))) {
			System.out.println("用户列表中都是男性的用户");
		} else {
			System.out.println("用户列表中不全是男性的用户");
		}

		// 与allMatch相反，noneMatch检测流中元素全部不符合Predicate参数
		if (demoList.stream().noneMatch(user -> user.getAge() > 50)) {
			System.out.println("用户列表中没有大于50岁的用户");
		}

		// 以上的anyMatch、allMatch、noneMatch都是短路操作，因为它不需要处理流中所有元素就能得到结果

		// 筛选年龄大于/等于30岁的用户，并返回符合条件的任意一个
		// findAny会返回当前流中的任意一个，它返回Optional对象，ifPresent作用是如果存在对象则执行相关Consumer
		demoList.stream().filter(user -> user.getAge() >= 30).findAny().ifPresent(System.out::println);

		// Optional<User>如果不存在值，则使用orElse构造一个新的对象返回
		System.out.println(demoList.stream().filter(user -> user.getAge() > 60).findAny().orElse(new User("李老头", 75, "男")));

		// findFirst用于返回流中第一个元素
		System.out.println(demoList.stream().findFirst().get());
		// findAny也是相同的作用，但findFirst如果用在并行流中的话限制太多
	}
	
	/**
	 * 针对Stream进行排序操作
	 */
	public void comparing() {
		
		// 使用Stream的sorted方法传入比较器对象，按照用户年龄进行排序，并返回排序后的列表
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
		 * 利益于Comparator提供的新工厂方法comparing，还能更简单一点
		 */
		demoList.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
		
		/**
		 * Comparator的reversed方法作用是将排序进行逆序排列
		 */
		demoList.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
		
	}

	/**
	 * Reduce归约是终端操作，它将生成流的最终结果
	 * <br />
	 * Reduce接收一个BinaryOperator参数，函数描述符是(T-T)->T
	 */
	public void reduce() {

		// 求用户列表中所有用户的年龄总合
		demoList.stream().map(User::getAge).reduce(0, (u1, u2) -> u1 + u2);
		// 上述方法先将用户流映射成为年龄的Stream<Integer>流，再使用reduce进行年龄的合计操作。reduce第一个参数是初始值，第二个参数是
		// BinaryOperator<Integer>,将流中前后两个元素进行累加。
		demoList.stream().map(User::getAge).reduce((u1, u2) -> u1 + u2).get();
		// 上述方法没有初始值参数，因为可能流中没有任何数据，并且没有为其设置初始值，所以它返回的是一个Optional<Integer>
		// 还能更简单，sum方法用于将流中的int元素进行累加，之所以还需要使用mapToInt是因为Stream<Integer>并不支持sum操作，所以需要将其特化为IntStream，这等同于reduce(0, Integer::sum)
		demoList.stream().mapToInt(User::getAge).sum();
		// 特化流也可以使用boxed方法对元素进行装箱
		Stream<Integer> stream = IntStream.rangeClosed(1, 3).boxed();

		// 最大值、最小值
		demoList.stream().map(user -> user.getAge()).reduce(Integer::max);
		demoList.stream().map(user -> user.getAge()).reduce(Integer::min);
		
		// 元素数量
		demoList.stream().count();
		
		// 分组
		Map<String,List<User>> map = demoList.stream().collect(Collectors.groupingBy(User::getSex));
	}
	
	/**
	 * 构造流的方法
	 */
	public void createStream() {
		
		// 使用of方法构建一个Stream<User>流对象
		Stream<User> userStream = Stream.of(demoList.toArray(new User[] {}));
		
		// 构建空流
		Stream<User> emptyStream = Stream.empty();
		
		// 由数组构建流
		Stream<String> stringStream = Arrays.stream(new String[] {"a","b","c"});
		
		try {
			// 由文件获取Stream，lines方法流中的元素是文件中每一行的字符串
			Files.lines(Paths.get("")).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 使用iterate生成无限流
		Stream.iterate(0, n->n+1).limit(20).forEach(System.out::println);
		Stream.iterate(new int[] {0,1}, n->new int[] {n[1],n[0]+n[1]}).limit(20).forEach(System.out::println);
		
		// generate也同样生成无限流，但与iterate不同，他接受一个Supplier参数用于生成元素
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
