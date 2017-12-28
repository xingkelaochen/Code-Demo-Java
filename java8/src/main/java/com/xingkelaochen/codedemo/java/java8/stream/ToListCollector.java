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
 * ʵ���Լ���Collector
 * <br />
 * Collector<T,A,R>�������ͷֱ��ǣ�����Ҫ�ռ�Ԫ�ص����ͣ��ۼ��������ͣ��ռ������õ����ս��������
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
	 * �����µĽ���������ڶԿ���ִ�в�����ʱ���������Ҳ�����ռ��Ľ��
	 */
	@Override
	public Supplier<List<T>> supplier() {
		
		// �������ַ�ʽ������ͬ
		//return ()->new ArrayList<T>();
		return ArrayList::new;
	}
	
	/**
	 * ��Ԫ����ӵ�����������BiConsumer�����Ѻ����ӿ�����
	 */
	@Override
	public BiConsumer<List<T>, T> accumulator() {

		// �������ַ�ʽ������ͬ
		//return (list,t)->list.add(t);
		return List::add;
	}

	/**
	        �ڱ���������finisher�������뷵�����ۻ����̵����Ҫ���õ�һ���������Ա㽫�ۼ�
	        ������ת��Ϊ�������ϲ��������ս����ͨ��������ToListCollector�����һ�����ۼ�����
	        ��ǡ�÷���Ԥ�ڵ����ս��������������ת��������finisher����ֻ�践��identity����
	 */
	@Override
	public Function<List<T>, List<T>> finisher() {

		return Function.identity();
	}
	
	/**
	 * ��Լ������ �ϲ����и���������BinaryOperator����������List�ĺϲ���ֻ��Ҫ�ѵڶ������Ľ���ӵ���һ��������ĺ���
	 */
	@Override
	public BinaryOperator<List<T>> combiner() {
		
		return (list1,list2)->{
			list1.addAll(list2);
			return list2;
		};
	}
	
	/**
	 	��characteristics�᷵��һ�����ɱ��Characteristics���ϣ�������
	���ռ�������Ϊ���������ǹ������Ƿ���Բ��й�Լ���Լ�����ʹ����Щ�Ż�����ʾ��
		Characteristics��һ������������Ŀ��ö�١�
		UNORDERED������Լ�������������Ŀ�ı������ۻ�˳���Ӱ�졣
		CONCURRENT����accumulator�������ԴӶ���߳�ͬʱ���ã��Ҹ��ռ������Բ��й�
	Լ��������ռ���û�б�ΪUNORDERED����������������������Դʱ�ſ��Բ��й�Լ��
		IDENTITY_FINISH���������������������صĺ�����һ����Ⱥ�������������������
	����£��ۼ������󽫻�ֱ��������Լ���̵����ս������Ҳ��ζ�ţ����ۼ���A���Ӽ�
	���ת��Ϊ���R�ǰ�ȫ�ġ�
	 */
	@Override
	public Set<Characteristics> characteristics() {

		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
		
	}
	
}
