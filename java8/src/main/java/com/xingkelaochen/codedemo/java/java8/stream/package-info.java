/**
 * Jdk1.8中新增Stream的支持<br />
 * Stream建立在数据处理的概念上，相对于数据存概念的集合，Stream(流)不保存数据，只针对其中的元素进行数据操作。<br />
 * 
 * 源：每一个流都需要一个数据源，这个源可能是集合、数组、输入/输出资源，由列表生成的流顺序与列表一致。<br />
 * 元素序列：流中数据处理的最小单位。<br />
 * 数据处理操作：分为中间操作（map,limit,filter,distinct），与终端操作(collect,reduce,count,forEach)。可以顺序执行也可以并行执行。<br />
 * 流水线：流操作本身返回一个流水线，将操作链接起来。流水线的操作是能是按顺序进行一次的。
 * 内部迭代：与使用迭代器进行显示的迭代不同，流的迭代是内部自己进行的。
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
package com.xingkelaochen.codedemo.java.java8.stream;