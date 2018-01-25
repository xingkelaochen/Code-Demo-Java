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
 * Stream的collect收集操作<br />
 * collect操作也是归约操作，用于将流中的元素进行汇总
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
		demoList.add(new User("张三", 20, "男"));
		demoList.add(new User("李四", 30, "男"));
		demoList.add(new User("王麻子", 40, "男"));
		demoList.add(new User("刘五", 20, "男"));
		demoList.add(new User("白雪", 18, "女"));
		demoList.add(new User("陈红", 25, "女"));
	}

	/**
	 * Collectors类中提供了一些静态的工厂方法，可以进行简洁的collect操作<br />
	 * 本例静态导入了java.util.stream.Collectors中所有的静态工厂方法
	 */
	private void collect() {

		// 统计用户列表中用户的数量
		long num = demoList.stream().collect(counting());
		// 也可以写为，实际是使用的mapToLong(e -> 1L).sum()的方式
		demoList.stream().count();

		// 找出年龄最大值/最小值 maxBy/minBy，reversed方法用于将排列顺序反转
		Comparator<User> comparator = Comparator.comparingInt(User::getAge).reversed();
		Optional<User> user = demoList.stream().collect(maxBy(comparator));
		user = demoList.stream().collect(minBy(comparator));
		// 也可以使用Stream的sorted排序，再获取第一个元素的方式
		user = demoList.stream().sorted(comparator).findFirst();

		// 合计用户年龄
		Integer ageSum = demoList.stream().collect(summingInt(User::getAge));

		// 计算用户平均年龄
		double ageAvg = demoList.stream().collect(averagingInt(User::getAge));

		// 获取各统计值的集合，包括 count、sum、min、max、avg
		IntSummaryStatistics statistics = demoList.stream().collect(summarizingInt(User::getAge));

		// 拼接所有用户名字
		String nameStr = demoList.stream().map(User::getName).collect(joining());
		nameStr = demoList.stream().map(User::getName).collect(joining(","));

		// 上述所有使用Collectors静态方法的都只是为了方便代码编写，实际它用的都是redecing方法
		// 比如示年龄总和可以写成如下这样
		ageSum = demoList.stream().collect(reducing(0, User::getAge, (i, j) -> i + j));
		// reducing接受三个参数：初始值，Function，
		// BinaryOperator，其中Function用于映射流中的元素为需要统计汇总的类型，BinaryOperator是统计汇总的操作

		// 下面获取年龄最大的用户示例，没有初始值与Function转换两个参数，返回Optional对象
		user = demoList.stream().collect(reducing((i, j) -> i.getAge() > j.getAge() ? i : j));

		// 分组
		// 按性别属性简单分组
		Map<String, List<User>> groupMap = demoList.stream().collect(groupingBy(User::getSex));
		// 自定义分组实现
		groupMap = demoList.stream().collect(groupingBy(u -> {
			if (u.getSex().equals("男")) {
				return "男性";
			} else {
				return "女性";
			}
		}));
		// 多级分组，先用性别分，再以年龄进行自定义分组
		Map<String, Map<String, List<User>>> muitGroupMap = demoList.stream()
				.collect(groupingBy(User::getSex, groupingBy(u -> {
					if (u.getAge() > 20) {
						return "A";
					} else {
						return "B";
					}
				})));
		// 分组统计个数
		Map<String, Long> groupCount = demoList.stream().collect(groupingBy(User::getSex, counting()));
		// 分组统计每个 性别年龄最大的。因为maxBy返回的是Optional对象，但其实在groupingBy中不会存在
		Map<String, Optional<User>> map = demoList.stream()
				.collect(groupingBy(User::getSex, maxBy(Comparator.comparing(User::getAge))));
		// collectingAndThen用于将收集返回的结果转换为另一种类型，此例就将上例中无用的Optional对象直接调用其get方法进行转换
		Map<String, User> map2 = demoList.stream().collect(
				groupingBy(User::getSex, collectingAndThen(maxBy(Comparator.comparing(User::getAge)), Optional::get)));

		// 分区
		// 分区是分组的特殊形式，授受Predicate谓词进行判定，返回Map<Boolean,List<T>>类型的对象
		// 以是否男性为条件进行用户列表分区
		Map<Boolean, List<User>> map3 = demoList.stream().collect(partitioningBy(u -> {
			if (u.getSex().equals("男")) {
				return true;
			} else {
				return false;
			}
		}));
		map3.get(true);
		// 如果要获取男性用户列表，则上边的方式等同于下边使用filter的方式
		demoList.stream().filter(u -> u.getSex().equals("男")).collect(toList());
		// 以是否男性分区，再以年龄分组
		Map<Boolean, Map<Integer, List<User>>> map4 = demoList.stream().collect(partitioningBy(u -> {
			if (u.getSex().equals("男")) {
				return true;
			} else {
				return false;
			}
		}, groupingBy(User::getAge)));
	}

	/**
	 * 使用自定义的Collector实现
	 * 本例演示了一个类似于Collectos.toList()静态工厂方法的Collector实现类，虽然两者功能相同，但还是有所区别的
	 * 
	 * @see ToListCollector
	 * 
	 */
	public void collectorInterface() {

		User[] arr = demoList.toArray(new User[] {});

		List<User> userList = Arrays.stream(arr).collect(new ToListCollector<User>());

		System.out.println(userList);

		// 当然也可以不用实现Collector的接口，直接在collect方法中定义
		// 注意，这种方式的combiner不能传递任何的Characteristics的参数，所以永远是一个IDENTITY_FINISH、CONCURRENT但并非UNORDERED的收集器。
		// 标记为IDENTITY_FINISH则说明累加器返回的结果是最终的结果，不需要再进行转换。（所以第三个参数BiConsumer不会执行）
		Arrays.stream(arr).collect(ArrayList::new, List::add, (list1, list2) -> list1.addAll(list2));

	}

	/**
	 * Stream的reduce与collect方法比较
	 * 
	 */
	public void reduceOrCollect() {

		// 求用户年龄总计
		int sum = demoList.stream().map(User::getAge).reduce((i, j) -> i + j).orElse(0);
		sum = demoList.stream().collect(reducing(0, User::getAge, (i, j) -> i + j));
		sum = demoList.stream().collect(summingInt(User::getAge));
		sum = demoList.stream().mapToInt(User::getAge).sum();

		// 将流转换为List输出
		List<User> userList = demoList.stream().reduce(new ArrayList<User>(), (List<User> l, User u) -> {
			l.add(u);
			return l;
		}, (List<User> l1, List<User> l2) -> {

			// 与collect方法一样，此处的combiner并不执行，因为不能传递任何的Characteristics的参数，所以永远是一个IDENTITY_FINISH、CONCURRENT但并非UNORDERED的收集器。
			return null;
		});
		// 这种需求不得不使用reduce(List<User> identity, BiFunction<List<User>, ? super User,List<User>> accumulator, BinaryOperator<List<User>> combiner)方法
		// 首先归约的初始值是一个空的List<User>列表
		// 将每个元素包含进List<User>列表中返回
		// 再将前一个List的值合并，最终返回总的List
		// 而使用collect就很简单了
		userList = demoList.stream().collect(Collectors.toList());

		// 函数式编程提供了一个需求的多种实现，应该尽可能使用简化特化的方式，比如mapToInt(ToIntFunction).sum()
		// 当然这两种方式还有其他区别，比如在并行计算上，详细看Collector的自定义实现中characteristics()

	}

	public static void main(String[] args) {

		CollectDemo demo = new CollectDemo();

		demo.collectorInterface();
	}

}
