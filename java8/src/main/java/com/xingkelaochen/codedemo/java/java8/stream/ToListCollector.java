package com.xingkelaochen.codedemo.java.java8.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 
 * 实现自己的Collector
 * <br />
 * Collector<T,A,R>三个类型分别是：流中要收集元素的类型，累加器的类型，收集操作得到最终结果的类型
 * 
 * @author xingkelaochen
 * 
 * <p>
 * E-MAIL: admin@xingkelaochen.com
 * <br />
 * SITE: http://www.xingkelaochen.com
 * </p>
 *
 * @param <T>
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

	/**
	 * 建立新的结果容器，在对空流执行操作的时候，这个容器也将是收集的结果
	 */
	@Override
	public Supplier<List<T>> supplier() {
		
		// 以下两种方式作用相同
		//return ()->new ArrayList<T>();
		return ArrayList::new;
	}
	
	/**
	 * 将元素添加到容器，返回BiConsumer的消费函数接口类型
	 */
	@Override
	public BiConsumer<List<T>, T> accumulator() {

		// 以下两种方式作用相同
		//return (list,t)->list.add(t);
		return List::add;
	}

	/**
	        在遍历完流后，finisher方法必须返回在累积过程的最后要调用的一个函数，以便将累加
	        器对象转换为整个集合操作的最终结果。通常，就像ToListCollector的情况一样，累加器对
	        象恰好符合预期的最终结果，因此无需进行转换。所以finisher方法只需返回identity函数
	 */
	@Override
	public Function<List<T>, List<T>> finisher() {

		return Function.identity();
	}
	
	/**
	 * 归约操作， 合并流中各个容器的BinaryOperator操作，对于List的合并则只需要把第二个流的结果加到第一个流结果的后面
	 */
	@Override
	public BinaryOperator<List<T>> combiner() {
		
		return (list1,list2)->{
			list1.addAll(list2);
			return list2;
		};
	}
	
	/**
	 	—characteristics会返回一个不可变的Characteristics集合，它定义
	了收集器的行为——尤其是关于流是否可以并行归约，以及可以使用哪些优化的提示。
		Characteristics是一个包含三个项目的枚举。
		UNORDERED——归约结果不受流中项目的遍历和累积顺序的影响。
		CONCURRENT——accumulator函数可以从多个线程同时调用，且该收集器可以并行归
	约流。如果收集器没有标为UNORDERED，那它仅在用于无序数据源时才可以并行归约。
		IDENTITY_FINISH——这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种
	情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器A不加检
	查地转换为结果R是安全的。
	 */
	@Override
	public Set<Characteristics> characteristics() {

		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
		
	}
	
}
