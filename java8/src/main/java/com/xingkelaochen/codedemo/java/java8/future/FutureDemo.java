package com.xingkelaochen.codedemo.java.java8.future;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * 演示Future的用法
 * 
 * <p>
 * 调用模拟的远程服务存根{@link MockRemoteService}
 * </p>
 *
 * @author xingkelaochen
 * 
 *         <p>
 *         E-MAIL: admin@xingkelaochen.com <br />
 *         SITE: http://www.xingkelaochen.com
 *         </p>
 *
 */
public class FutureDemo {

	private MockRemoteService service = new MockRemoteService();

	/**
	 * 模拟调用三次远程运算服务，最终将三次结果求平均值 <br />
	 * 在同步的执行模式下，调用服务必须等待之前的服务返回后才可执行。在本例中，三次远程服务调用无依赖关系，但后边的服务调用却必须得等待前边的返回结果后才可开始，所以这种方式耗时最长。
	 * 
	 * @param a
	 *            需要运算的a整数
	 * @param b
	 *            需要运算的b整数
	 * @return 三次调用结果的平均值
	 */
	public int syncProcess(int a, int b) {

		try {

			long init = System.currentTimeMillis();
			long now = init;

			// 两个参数相加
			int result = service.service(a + "+" + b);
			System.out.println("第一次同步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："
					+ (System.currentTimeMillis() - now) + "毫秒");

			// 两个参数相减
			now = System.currentTimeMillis();
			int result2 = service.service(a + "-" + b);
			System.out.println("第二次同步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："
					+ (System.currentTimeMillis() - now) + "毫秒");

			// 两个参数相乘
			now = System.currentTimeMillis();
			int result3 = service.service(a + "*" + b);
			System.out.println("第三次同步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："
					+ (System.currentTimeMillis() - now) + "毫秒");

			int sum = result + result2 + result3;

			System.out.println("同步方法执行总耗时：" + (System.currentTimeMillis() - init) + "毫秒");

			// 因为固定延迟1秒，并且三次服务调用按顺序阻塞，所以执行时间在3秒以上

			return sum / 3;

		} catch (Exception ex) {
			ex.printStackTrace();

			return -1;
		}

	}

	/**
	 * 同样还是执行syncProcess方法中对于两个参数的相同操作，使用异步阻塞式的Future进行处理 <br />
	 * 在使用Future的异步阻塞模式下，虽然get方法在子线程还未返回的前提下会阻塞，但是可以在get方法前进行其他操作来合理利用子线程返回的等待时间
	 * <br />
	 * 异步阻塞式：开启新线程进行服务访问，但调用者线程还是阻塞等待子线程的返回结果
	 * 
	 * @param a
	 *            需要运算的a整数
	 * @param b
	 *            需要运算的b整数
	 * @return 三次调用结果的平均值
	 */
	public int asyncBlockProcessByFuture(int a, int b) {

		try {
			ExecutorService executoer = Executors.newCachedThreadPool();

			long init = System.currentTimeMillis();
			long now = init;

			Future<Integer> result = executoer.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					// 两个参数相加
					int result = service.service(a + "+" + b);
					return result;
				}
			});

			System.out.println("第一次异步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："
					+ (System.currentTimeMillis() - now) + "毫秒");

			now = System.currentTimeMillis();
			Future<Integer> result2 = executoer.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					// 两个参数相减
					int result = service.service(a + "-" + b);
					return result;
				}
			});
			System.out.println("第二次异步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："
					+ (System.currentTimeMillis() - now) + "毫秒");

			now = System.currentTimeMillis();
			Future<Integer> result3 = executoer.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					// 两个参数相乘
					int result = service.service(a + "*" + b);
					return result;
				}
			});
			System.out.println("第三次异步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："
					+ (System.currentTimeMillis() - now) + "毫秒");

			// dosomething……

			// 直到此，三次服务调用因为异步的原因，程序并不需要等待服务接口返回，所以基本无耗时，并且在此之前可以进行其他操作……

			int sum = result.get() + result2.get() + result3.get();

			System.out.println("异步阻塞式[Future]方法执行总耗时：" + (System.currentTimeMillis() - init) + "毫秒");

			// 可以看到，因为固定延时1秒的原因，异步方法基本在1秒完成

			return sum / 3;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * 同样还是执行asyncBlockProcess方法中对于两个参数的相同操作，使用JDK1.8中新加入的CompletableFuture进行处理
	 * <br />
	 * CompletableFuture的get方法与Future一样，在得到返回结果前也会阻塞在调用线程中的get方法处，只是用法不同而已。 <br />
	 * complete方法在子线程中将结果通知给CompletableFuturer；并且使用completeExceptionally也能将子线程中的异常传递回调用者，这时调用者将能在ExecutionException异常中获取具体信息
	 * 
	 * @param a
	 *            需要运算的a整数
	 * @param b
	 *            需要运算的b整数
	 * @return 三次调用结果的平均值
	 */
	public int asyncBlockProcessByCompletableFuture(int a, int b) {

		try {

			long init = System.currentTimeMillis();
			long now = init;

			CompletableFuture<Integer> result = new CompletableFuture<>();
			new Thread(() -> {
				try {
					// 两个参数相加
					int val = service.service(a + "+" + b);
					result.complete(val);
				} catch (Exception ex) {
					result.completeExceptionally(ex);
				}
			}).start();
			System.out.println("第一次异步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："
					+ (System.currentTimeMillis() - now) + "毫秒");

			now = System.currentTimeMillis();
			CompletableFuture<Integer> result2 = new CompletableFuture<>();
			new Thread(() -> {
				try {
					// 两个参数相减
					int val = service.service(a + "-" + b);
					result2.complete(val);
				} catch (Exception ex) {
					result2.completeExceptionally(ex);
				}
			}).start();
			System.out.println("第二次异步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："
					+ (System.currentTimeMillis() - now) + "毫秒");

			now = System.currentTimeMillis();
			CompletableFuture<Integer> result3 = new CompletableFuture<>();
			new Thread(() -> {
				try {
					// 两个参数相乘
					int val = service.service(a + "*" + b);
					result3.complete(val);
				} catch (Exception ex) {
					result3.completeExceptionally(ex);
				}
			}).start();
			System.out.println("第三次异步调用服务总耗时：" + (System.currentTimeMillis() - init) + "毫秒，本次调用耗时："
					+ (System.currentTimeMillis() - now) + "毫秒");

			// dosomething……

			// 直到此，三次服务调用因为异步的原因，程序并不需要等待服务接口返回，所以基本无耗时，并且在此之前可以进行其他操作……

			int sum = result.get() + result2.get() + result3.get();

			System.out.println(
					"异步阻塞式[asyncBlockProcessByCompletableFuture]方法执行总耗时：" + (System.currentTimeMillis() - init) + "毫秒");

			// 可以看到，因为固定延时1秒的原因，异步方法基本在1秒完成

			return sum / 3;

		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * 为了使asyncBlockProcessByCompletableFutureSupplyAsync中的supplyAsync看起来更简洁，并且为了使用Stream能够打印执行时间，封装了模拟远程服务调用方法，并且将异常标记为RuntimeException
	 * 
	 * @param str
	 *            运算表达式
	 * @param logPrefix
	 *            日志前辍
	 * @return 计算结果
	 */
	private int invokeService(String str, String logPrefix) {
		try {
			long now = System.currentTimeMillis();

			int val = service.service(str);

			System.out.println(logPrefix + "服务本次调用耗时：" + (System.currentTimeMillis() - now) + "毫秒");
			return val;
		} catch (Exception ex) {
			throw new RuntimeException("info");
		}
	}

	/**
	 * 
	 * Completable提供了一个基于lambda的工厂方法，这样使代码精简、易读了许多 <br />
	 * supplyAsync工厂方法接受一个Supplier<T>的lambda表达式
	 * 
	 * @param a
	 *            需要运算的a整数
	 * @param b
	 *            需要运算的b整数
	 * @return 三次调用结果的平均值
	 */
	public int asyncBlockProcessByCompletableFutureSupplyAsync(int a, int b) {

		try {
			long init = System.currentTimeMillis();

			// 两个参数相加
			CompletableFuture<Integer> future = CompletableFuture
					.supplyAsync(() -> invokeService(a + "+" + b, "第一次同步"));

			// 两个参数相减
			CompletableFuture<Integer> future2 = CompletableFuture
					.supplyAsync(() -> invokeService(a + "-" + b, "第二次同步"));

			// 两个参数相乘
			CompletableFuture<Integer> future3 = CompletableFuture
					.supplyAsync(() -> invokeService(a + "*" + b, "第三次同步"));

			int sum = future.get() + future2.get() + future3.get();
			System.out.println("异步阻塞式[asyncBlockProcessByCompletableFutureSupplyAsync]方法执行总耗时："
					+ (System.currentTimeMillis() - init) + "毫秒");

			// 同样，这种异步阻塞式的调用方式，因为模拟固定延迟1秒，所以方法执行的总耗时也就比1秒多一点……

			return sum / 3;

		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * 使用Stream的方法演示同步执行 <br />
	 * 由于本例的情景并无实际列表，所以使用rangeClosed来模拟三次循环调用，为了方便演示代码，此处都以 相加 运算为例
	 * 
	 * @param a
	 *            需要运算的a整数
	 * @param b
	 *            需要运算的b整数
	 * @return 三次调用结果的平均值
	 */
	public int syncProcessByStream(int a, int b) {

		long init = System.currentTimeMillis();

		OptionalInt sum = IntStream.rangeClosed(1, 3).map((x) -> invokeService(a + "+" + b, "第" + x + "次同步"))
				.reduce(Integer::sum);

		int result = sum.getAsInt() / 3;

		System.out.println("同步阻塞式[syncProcessByStream]方法执行总耗时：" + (System.currentTimeMillis() - init) + "毫秒");

		// 因为同样是顺序阻塞，在每次模拟固定延迟1秒的情况下，依然需要3秒以上……

		return result;
	}

	/**
	 * 
	 * 使用Stream的方法演示异步并行执行 ，parallelStream的流会开启新线程分别去执行每个元素的操作<br />
	 * 由于本例的情景并无实际列表，所以先将循环的封装成List<Integer>列表，再使用parallelStream并行流，为了方便演示代码，此处都以 相加
	 * 运算为例
	 * 
	 * @param a
	 *            需要运算的a整数
	 * @param b
	 *            需要运算的b整数
	 * @return 三次调用结果的平均值
	 */
	public int asyncBlockProcessByStream(int a, int b) {

		long init = System.currentTimeMillis();

		List<Integer> list = IntStream.rangeClosed(1, 3).boxed().collect(Collectors.toList());

		Optional<Integer> sum = list.parallelStream().map((x) -> invokeService(a + "+" + b, "第" + x + "次异步"))
				.reduce(Integer::sum);

		int result = sum.get() / 3;

		System.out.println("异步阻塞式[asyncBlockProcessByStream]方法执行总耗时：" + (System.currentTimeMillis() - init) + "毫秒");

		// 同样，这种使用并行流的异步阻塞式的调用方式，因为模拟固定延迟1秒，所以方法执行的总耗时也就1393毫秒

		return result;
	}

	/**
	 * 将CompletableFuture与Stream结合起来看看<br />
	 * 此方法使用了两个流水线来处理，否则两个map来处理前一个CompletableFuture的join方法会阻塞下一个CompletableFuture，又回到了同步顺序执行的情景。<br
	 * />
	 * 为了方便演示代码，此处都以 相加 运算为例
	 * 
	 * @param a
	 *            需要运算的a整数
	 * @param b
	 *            需要运算的b整数
	 * @return 三次调用结果的平均值
	 */
	public int asyncBlockProcessByCompletableFutureAndStream(int a, int b) {

		long init = System.currentTimeMillis();

		List<CompletableFuture<Integer>> futureList = IntStream.rangeClosed(1, 3).boxed()
				.map((x) -> CompletableFuture.supplyAsync(() -> invokeService(a + "+" + b, "第" + x + "次")))
				.collect(Collectors.toList());

		List<Integer> valList = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());

		System.out.println(
				"异步阻塞式[CompletableFutureSupplyAsyncAndStream]方法执行总耗时：" + (System.currentTimeMillis() - init) + "毫秒");

		// 在模拟固定延迟1秒的前提下，使用CompletableFuture两个流水线的操作方式的总耗时1016毫秒，似乎比并行流快一点……

		return valList.stream().reduce(Integer::sum).get() / 3;

	}

	/**
	 * 
	 * 让我们把问题搞的稍微复杂一点，增加请求的次数，把使用parallelStream并行流，与使用CompletableFuture两个流水线做个对比
	 * 
	 * @param a
	 *            需要运算的a整数
	 * @param b
	 *            需要运算的b整数
	 */
	public void asyncBlockProcessCompare(int a, int b) {

		// 请求次数
		int loopCount = 4;

		// 使用parallelStream并行流的处理方式
		long init = System.currentTimeMillis();
		List<Integer> list = IntStream.rangeClosed(1, loopCount).boxed().collect(Collectors.toList());

		Optional<Integer> sum = list.parallelStream().map((x) -> invokeService(a + "+" + b, "第" + x + "次异步"))
				.reduce(Integer::sum);


		System.out.println("异步阻塞式[asyncBlockProcessByStream]方法执行总耗时：" + (System.currentTimeMillis() - init) + "毫秒");

		// 使用CompletableFuture两个流水线的处理方式
		init = System.currentTimeMillis();
		List<CompletableFuture<Integer>> futureList = IntStream.rangeClosed(1, loopCount).boxed()
				.map((x) -> CompletableFuture.supplyAsync(() -> invokeService(a + "+" + b, "第" + x + "次")))
				.collect(Collectors.toList());

		System.out.println("当前运行环境线程数：" + Runtime.getRuntime().availableProcessors()
				+ "，异步阻塞式[CompletableFutureSupplyAsyncAndStream]方法执行总耗时：" + (System.currentTimeMillis() - init) + "毫秒");

		// 将请求参数loopCount为4，模拟延时依然1秒
		// 并行流的方式总耗时1380毫秒，与3次基本无差别，似乎还快那么一丁点。
		// 可是使用CompletableFuture的两个流水线方式，耗时竟然高达2017毫秒，郁闷了吗？

		// 将请求参数loopCount再次改为5
		// 并行流的方式总耗时2394毫秒，使用CompletableFuture的两个流水线方式总耗时2020毫秒。
		// 使用CompletableFuture的两个流水线方式明显比使用并行流的方式快

		// 总结：这与当前运行环境的线程数有直接关系，试想若当前执行线程大于可用线程，当然要有线程需要等待，这样耗时自然会增加。
		// 本示例Runtime.getRuntime().availableProcessors()的值为4，所以当第一个流水线使用CompletableFuture进行4次请求后，第二个流水线自然要等待线程释放，所以耗时才有所增加

	}

	/**
	 * 上边的例子说明了并行任务与线程的关系，CompletableFuture可以自定义执行器的线程数，这样就可以按照业务进行合理的性能优化
	 * 
	 * @param a
	 *            需要运算的a整数
	 * @param b
	 *            需要运算的b整数
	 * @return 九次调用结果的平均值
	 */
	public int withExecutor(int a, int b) {

		int loopCount = 9;

		// 执行器可以设置线程池的大小，但也需要设置一个上限，防止服务器崩溃
		Executor executor = Executors.newFixedThreadPool(Math.min(loopCount, 50), new ThreadFactory() {

			@Override
			public Thread newThread(Runnable arg0) {
				Thread t = new Thread(arg0);
				// 使用守护线程，保证程序退出可以一起关闭此线程
				t.setDaemon(true);
				return t;
			}
		});

		// 使用CompletableFuture两个流水线的处理方式
		long init = System.currentTimeMillis();
		List<CompletableFuture<Integer>> futureList = IntStream.rangeClosed(1, loopCount).boxed()
				.map((x) -> CompletableFuture.supplyAsync(() -> invokeService(a + "+" + b, "第" + x + "次"), executor))
				.collect(Collectors.toList());

		List<Integer> valList = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());

		System.out.println("当前运行环境线程数：" + Runtime.getRuntime().availableProcessors()
				+ "，异步阻塞式[CompletableFutureSupplyAsyncAndStream]方法执行总耗时：" + (System.currentTimeMillis() - init) + "毫秒");

		// 1秒固定模拟延时，按照前边使用通用线程数为4的情况推算，如果请求9次，那延迟最少需要3秒
		// 但是现在神奇了,9次请求居然只用了1388毫秒，这已经很快了，不是吗？yeah~
		// 问题：如果第一个请求远程服务的流换成parallelStream并行流，效率会不会更好一点？其实不见得，supplyAsync方法已经是异步的，效率不会提升而且新开的线程还可能会消耗性能。

		return valList.stream().reduce(Integer::sum).get() / 9;

	}
	
	// 那么问题来了？采用并行流的方式与使用CompletableFuture两个流水线的方式，在效率上差距很小，那么倒底使用哪一个呢？以下是建议：
	// 如果是计算密集型用并行流，如果涉及需要等待的IO操作那么果断使用CompletableFuture两个流水线的方式！

	public static void main(String[] args) {

		FutureDemo demo = new FutureDemo();

		demo.syncProcess(4, 5);

		demo.asyncBlockProcessByFuture(4, 5);

		demo.asyncBlockProcessByCompletableFuture(4, 5);

		demo.asyncBlockProcessByCompletableFutureSupplyAsync(4, 5);

		demo.syncProcessByStream(4, 5);

		demo.asyncBlockProcessByStream(4, 5);

		demo.asyncBlockProcessByCompletableFutureAndStream(4, 5);

		demo.asyncBlockProcessCompare(4, 5);

		demo.withExecutor(4, 5);
	}
}
