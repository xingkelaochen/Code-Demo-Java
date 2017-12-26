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
import java.util.stream.Stream;

/**
 * 
 * ��ʾFuture���÷�
 * 
 * <p>
 * ģ����õ�Զ�̷�����{@link MockRemoteService}
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
	 * ģ���������Զ������������ս����ν����ƽ��ֵ <br />
	 * ��ͬ����ִ��ģʽ�£����÷������ȴ�֮ǰ�ķ��񷵻غ�ſ�ִ�С��ڱ����У�����Զ�̷��������������ϵ������ߵķ������ȴ����õȴ�ǰ�ߵķ��ؽ����ſɿ�ʼ���������ַ�ʽ��ʱ���
	 * 
	 * @param a
	 *            ��Ҫ�����a����
	 * @param b
	 *            ��Ҫ�����b����
	 * @return ���ε��ý����ƽ��ֵ
	 */
	public int syncProcess(int a, int b) {

		try {

			long init = System.currentTimeMillis();
			long now = init;

			// �����������
			int result = service.service(a + "+" + b);
			System.out.println("��һ��ͬ�����÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"
					+ (System.currentTimeMillis() - now) + "����");

			// �����������
			now = System.currentTimeMillis();
			int result2 = service.service(a + "-" + b);
			System.out.println("�ڶ���ͬ�����÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"
					+ (System.currentTimeMillis() - now) + "����");

			// �����������
			now = System.currentTimeMillis();
			int result3 = service.service(a + "*" + b);
			System.out.println("������ͬ�����÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"
					+ (System.currentTimeMillis() - now) + "����");

			int sum = result + result2 + result3;

			System.out.println("ͬ������ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");

			// ��Ϊ�̶��ӳ�1�룬�������η�����ð�˳������������ִ��ʱ����3������

			return sum / 3;

		} catch (Exception ex) {
			ex.printStackTrace();

			return -1;
		}

	}

	/**
	 * ͬ������ִ��syncProcess�����ж���������������ͬ������ʹ���첽����ʽ��Future���д��� <br />
	 * ��ʹ��Future���첽����ģʽ�£���Ȼget���������̻߳�δ���ص�ǰ���»����������ǿ�����get����ǰ�������������������������̷߳��صĵȴ�ʱ��
	 * 
	 * @param a
	 *            ��Ҫ�����a����
	 * @param b
	 *            ��Ҫ�����b����
	 * @return ���ε��ý����ƽ��ֵ
	 */
	public int asyncProcessByFuture(int a, int b) {

		try {
			ExecutorService executoer = Executors.newCachedThreadPool();

			long init = System.currentTimeMillis();
			long now = init;

			Future<Integer> result = executoer.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					// �����������
					int result = service.service(a + "+" + b);
					return result;
				}
			});

			System.out.println("��һ���첽���÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"
					+ (System.currentTimeMillis() - now) + "����");

			now = System.currentTimeMillis();
			Future<Integer> result2 = executoer.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					// �����������
					int result = service.service(a + "-" + b);
					return result;
				}
			});
			System.out.println("�ڶ����첽���÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"
					+ (System.currentTimeMillis() - now) + "����");

			now = System.currentTimeMillis();
			Future<Integer> result3 = executoer.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					// �����������
					int result = service.service(a + "*" + b);
					return result;
				}
			});
			System.out.println("�������첽���÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"
					+ (System.currentTimeMillis() - now) + "����");

			// dosomething����

			// ֱ���ˣ����η��������Ϊ�첽��ԭ�򣬳��򲢲���Ҫ�ȴ�����ӿڷ��أ����Ի����޺�ʱ�������ڴ�֮ǰ���Խ���������������

			int sum = result.get() + result2.get() + result3.get();

			System.out.println("�첽����[asyncProcessByFuture]����ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");

			// ���Կ�������Ϊ�̶���ʱ1���ԭ���첽����������1�����

			return sum / 3;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * ͬ������ִ��asyncProcess�����ж���������������ͬ������ʹ��JDK1.8���¼����CompletableFuture���д��� <br />
	 * CompletableFuture��Futureһ����������ʹ��get������ȡ���ؽ�� <br />
	 * ʹ��complete���������߳��н����֪ͨ��CompletableFuturer������ʹ��completeExceptionallyҲ�ܽ����߳��е��쳣���ݻص����ߣ���ʱ�����߽�����ExecutionException�쳣�л�ȡ������Ϣ
	 * 
	 * @param a
	 *            ��Ҫ�����a����
	 * @param b
	 *            ��Ҫ�����b����
	 * @return ���ε��ý����ƽ��ֵ
	 */
	public int asyncProcessByCompletableFuture(int a, int b) {

		try {

			long init = System.currentTimeMillis();
			long now = init;

			CompletableFuture<Integer> result = new CompletableFuture<>();
			new Thread(() -> {
				try {
					// �����������
					int val = service.service(a + "+" + b);
					result.complete(val);
				} catch (Exception ex) {
					result.completeExceptionally(ex);
				}
			}).start();
			System.out.println("��һ���첽���÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"
					+ (System.currentTimeMillis() - now) + "����");

			now = System.currentTimeMillis();
			CompletableFuture<Integer> result2 = new CompletableFuture<>();
			new Thread(() -> {
				try {
					// �����������
					int val = service.service(a + "-" + b);
					result2.complete(val);
				} catch (Exception ex) {
					result2.completeExceptionally(ex);
				}
			}).start();
			System.out.println("�ڶ����첽���÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"
					+ (System.currentTimeMillis() - now) + "����");

			now = System.currentTimeMillis();
			CompletableFuture<Integer> result3 = new CompletableFuture<>();
			new Thread(() -> {
				try {
					// �����������
					int val = service.service(a + "*" + b);
					result3.complete(val);
				} catch (Exception ex) {
					result3.completeExceptionally(ex);
				}
			}).start();
			System.out.println("�������첽���÷����ܺ�ʱ��" + (System.currentTimeMillis() - init) + "���룬���ε��ú�ʱ��"
					+ (System.currentTimeMillis() - now) + "����");

			// dosomething����

			// ֱ���ˣ����η��������Ϊ�첽��ԭ�򣬳��򲢲���Ҫ�ȴ�����ӿڷ��أ����Ի����޺�ʱ�������ڴ�֮ǰ���Խ���������������

			int sum = result.get() + result2.get() + result3.get();

			System.out.println(
					"�첽����[asyncProcessByCompletableFuture]����ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");

			// ���Կ�������Ϊ�̶���ʱ1���ԭ���첽����������1�����

			return sum / 3;

		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * Ϊ��ʹasyncProcessByCompletableFutureSupplyAsync�е�supplyAsync����������࣬����Ϊ��ʹ��Stream�ܹ���ӡִ��ʱ�䣬��װ��ģ��Զ�̷�����÷��������ҽ��쳣���ΪRuntimeException
	 * 
	 * @param str
	 *            ������ʽ
	 * @param logPrefix
	 *            ��־ǰ�
	 * @return ������
	 */
	private int invokeService(String str, String logPrefix) {
		try {
			long now = System.currentTimeMillis();

			int val = service.service(str);

			System.out.println(Thread.currentThread().getName() + " " + logPrefix + "������ú�ʱ��"
					+ (System.currentTimeMillis() - now) + "����");
			return val;
		} catch (Exception ex) {
			throw new RuntimeException("info");
		}
	}

	/**
	 * 
	 * Completable�ṩ��һ������lambda�Ĺ�������������ʹ���뾫���׶������ <br />
	 * supplyAsync������������һ��Supplier<T>��lambda���ʽ
	 * 
	 * @param a
	 *            ��Ҫ�����a����
	 * @param b
	 *            ��Ҫ�����b����
	 * @return ���ε��ý����ƽ��ֵ
	 */
	public int asyncProcessByCompletableFutureSupplyAsync(int a, int b) {

		try {
			long init = System.currentTimeMillis();

			// �����������
			CompletableFuture<Integer> future = CompletableFuture
					.supplyAsync(() -> invokeService(a + "+" + b, "��һ��ͬ��"));

			// �����������
			CompletableFuture<Integer> future2 = CompletableFuture
					.supplyAsync(() -> invokeService(a + "-" + b, "�ڶ���ͬ��"));

			// �����������
			CompletableFuture<Integer> future3 = CompletableFuture
					.supplyAsync(() -> invokeService(a + "*" + b, "������ͬ��"));

			int sum = future.get() + future2.get() + future3.get();
			System.out.println("�첽����[asyncProcessByCompletableFutureSupplyAsync]����ִ���ܺ�ʱ��"
					+ (System.currentTimeMillis() - init) + "����");

			// ͬ���������첽���еĵ��÷�ʽ����Ϊģ��̶��ӳ�1�룬���Է���ִ�е��ܺ�ʱҲ�ͱ�1���һ�㡭��

			return sum / 3;

		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * ʹ��Stream�ķ�����ʾͬ��ִ�� <br />
	 * ���ڱ������龰����ʵ���б�����ʹ��rangeClosed��ģ������ѭ�����ã�Ϊ�˷�����ʾ���룬�˴����� ��� ����Ϊ��
	 * 
	 * @param a
	 *            ��Ҫ�����a����
	 * @param b
	 *            ��Ҫ�����b����
	 * @return ���ε��ý����ƽ��ֵ
	 */
	public int syncProcessByStream(int a, int b) {

		long init = System.currentTimeMillis();

		OptionalInt sum = IntStream.rangeClosed(1, 3).map((x) -> invokeService(a + "+" + b, "��" + x + "��ͬ��"))
				.reduce(Integer::sum);

		int result = sum.getAsInt() / 3;

		System.out.println("ͬ��[syncProcessByStream]����ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");

		// ��Ϊͬ����˳����������ÿ��ģ��̶��ӳ�1�������£���Ȼ��Ҫ3�����ϡ���

		return result;
	}

	/**
	 * 
	 * ʹ��Stream�ķ�����ʾ�첽����ִ�� ��parallelStream�����Ὺ�����̷ֱ߳�ȥִ��ÿ��Ԫ�صĲ���<br />
	 * ���ڱ������龰����ʵ���б������Ƚ�ѭ���ķ�װ��List<Integer>�б���ʹ��parallelStream��������Ϊ�˷�����ʾ���룬�˴����� ���
	 * ����Ϊ��
	 * 
	 * @param a
	 *            ��Ҫ�����a����
	 * @param b
	 *            ��Ҫ�����b����
	 * @return ���ε��ý����ƽ��ֵ
	 */
	public int asyncProcessByStream(int a, int b) {

		long init = System.currentTimeMillis();

		List<Integer> list = IntStream.rangeClosed(1, 3).boxed().collect(Collectors.toList());

		Optional<Integer> sum = list.parallelStream().map((x) -> invokeService(a + "+" + b, "��" + x + "���첽"))
				.reduce(Integer::sum);

		int result = sum.get() / 3;

		System.out.println("�첽����[asyncProcessByStream]����ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");

		// ͬ��������ʹ�ò��������첽���еķ�ʽ����Ϊģ��̶��ӳ�1�룬���Է���ִ�е��ܺ�ʱҲ��1393����

		return result;
	}

	/**
	 * ��CompletableFuture��Stream�����������<br />
	 * �˷���ʹ����������ˮ����������������map������ǰһ��CompletableFuture��join������������һ��CompletableFuture���ֻص���ͬ��˳��ִ�е��龰��<br
	 * />
	 * Ϊ�˷�����ʾ���룬�˴����� ��� ����Ϊ��
	 * 
	 * @param a
	 *            ��Ҫ�����a����
	 * @param b
	 *            ��Ҫ�����b����
	 * @return ���ε��ý����ƽ��ֵ
	 */
	public int asyncProcessByCompletableFutureAndStream(int a, int b) {

		long init = System.currentTimeMillis();

		List<CompletableFuture<Integer>> futureList = IntStream.rangeClosed(1, 3).boxed()
				.map((x) -> CompletableFuture.supplyAsync(() -> invokeService(a + "+" + b, "��" + x + "��")))
				.collect(Collectors.toList());

		List<Integer> valList = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());

		System.out.println(
				"�첽����[CompletableFutureSupplyAsyncAndStream]����ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");

		// ��ģ��̶��ӳ�1���ǰ���£�ʹ��CompletableFuture������ˮ�ߵĲ�����ʽ���ܺ�ʱ1016���룬�ƺ��Ȳ�������һ�㡭��

		return valList.stream().reduce(Integer::sum).get() / 3;

	}

	/**
	 * 
	 * �����ǰ���������΢����һ�㣬��������Ĵ�������ʹ��parallelStream����������ʹ��CompletableFuture������ˮ�������Ա�<br
	 * />
	 * Ϊ�˷�����ʾ���룬�˴����� ��� ����Ϊ��
	 * 
	 * @param a
	 *            ��Ҫ�����a����
	 * @param b
	 *            ��Ҫ�����b����
	 */
	public void asyncProcessCompare(int a, int b) {

		// �������
		int loopCount = 4;

		// ʹ��parallelStream�������Ĵ���ʽ
		long init = System.currentTimeMillis();
		List<Integer> list = IntStream.rangeClosed(1, loopCount).boxed().collect(Collectors.toList());

		Optional<Integer> sum = list.parallelStream().map((x) -> invokeService(a + "+" + b, "��" + x + "���첽"))
				.reduce(Integer::sum);

		System.out.println("�첽����������ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");

		// ʹ��CompletableFuture������ˮ�ߵĴ���ʽ
		init = System.currentTimeMillis();
		List<CompletableFuture<Integer>> futureList = IntStream.rangeClosed(1, loopCount).boxed()
				.map((x) -> CompletableFuture.supplyAsync(() -> invokeService(a + "+" + b, "��" + x + "��")))
				.collect(Collectors.toList());

		System.out.println("��ǰ���л����߳�����" + Runtime.getRuntime().availableProcessors() + "���첽����CompletableFuture����ִ���ܺ�ʱ��"
				+ (System.currentTimeMillis() - init) + "����");

		// ���������loopCountΪ4��ģ����ʱ��Ȼ1��
		// �������ķ�ʽ�ܺ�ʱ1380���룬��3�λ����޲���ƺ�������ôһ���㡣
		// ����ʹ��CompletableFuture��������ˮ�߷�ʽ����ʱ��Ȼ�ߴ�2017���룬��������

		// ���������loopCount�ٴθ�Ϊ5
		// �������ķ�ʽ�ܺ�ʱ2394���룬ʹ��CompletableFuture��������ˮ�߷�ʽ�ܺ�ʱ2020���롣
		// ʹ��CompletableFuture��������ˮ�߷�ʽ���Ա�ʹ�ò������ķ�ʽ��

		// �ܽ᣺���뵱ǰ���л������߳�����ֱ�ӹ�ϵ����������ǰִ���̴߳��ڿ����̣߳���ȻҪ���߳���Ҫ�ȴ���������ʱ��Ȼ�����ӡ�
		// ��ʾ��Runtime.getRuntime().availableProcessors()��ֵΪ4�����Ե���һ����ˮ��ʹ��CompletableFuture����4������󣬵ڶ�����ˮ����ȻҪ�ȴ��߳��ͷţ����Ժ�ʱ�Ż�����Ӧ������

	}

	/**
	 * �ϱߵ�����˵���˲����������̵߳Ĺ�ϵ��CompletableFuture�����Զ���ִ�������߳����������Ϳ��԰���ҵ����к���������Ż�<br />
	 * Ϊ�˷�����ʾ���룬�˴����� ��� ����Ϊ��
	 * 
	 * @param a
	 *            ��Ҫ�����a����
	 * @param b
	 *            ��Ҫ�����b����
	 * @return �Ŵε��ý����ƽ��ֵ
	 */
	public int withExecutor(int a, int b) {

		int loopCount = 9;

		// ִ�������������̳߳صĴ�С����Ҳ��Ҫ����һ�����ޣ���ֹ����������
		Executor executor = Executors.newFixedThreadPool(Math.min(loopCount, 50), new ThreadFactory() {

			@Override
			public Thread newThread(Runnable arg0) {
				Thread t = new Thread(arg0);
				// ʹ���ػ��̣߳���֤�����˳�����һ��رմ��߳�
				t.setDaemon(true);
				return t;
			}
		});

		// ʹ��CompletableFuture������ˮ�ߵĴ���ʽ
		long init = System.currentTimeMillis();
		List<CompletableFuture<Integer>> futureList = IntStream.rangeClosed(1, loopCount).boxed()
				.map((x) -> CompletableFuture.supplyAsync(() -> invokeService(a + "+" + b, "��" + x + "��"), executor))
				.collect(Collectors.toList());

		List<Integer> valList = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());

		System.out.println("��ǰ���л����߳�����" + Runtime.getRuntime().availableProcessors()
				+ "���첽����[CompletableFutureSupplyAsyncAndStream]����ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");

		// 1��̶�ģ����ʱ������ǰ��ʹ��ͨ���߳���Ϊ4��������㣬�������9�Σ����ӳ�������Ҫ3��
		// ��������������,9�������Ȼֻ����1388���룬���Ѿ��ܿ��ˣ�������yeah~
		// ���⣺�����һ������Զ�̷����������parallelStream��������Ч�ʻ᲻�����һ�㣿��ʵ�����ã�supplyAsync�����Ѿ����첽�ģ�Ч�ʲ������������¿����̻߳����ܻ��������ܡ�

		return valList.stream().reduce(Integer::sum).get() / 9;

	}

	// ��ô�������ˣ����ò������ķ�ʽ��ʹ��CompletableFuture������ˮ�ߵķ�ʽ����Ч���ϲ���С����ô����ʹ����һ���أ������ǽ��飺
	// ����Ǽ����ܼ����ò�����������漰��Ҫ�ȴ���IO������ô����ʹ��CompletableFuture������ˮ�ߵķ�ʽ��

	/**
	 * 
	 * ���ϱߵķ����У����εķ������֮�䲢û��������ϵ<br />
	 * ��������������ģ���һ�ε��÷������������ӣ��������ȥ1���ڶ��ε��÷��񽫼��㽫����Ľ������ڶ�������ˡ�<br />
	 * 
	 * @param a
	 *            ��Ҫ�����a����
	 * @param b
	 *            ��Ҫ�����b����
	 * @return ���ý����ƽ��ֵ
	 */
	public int muitMap(int a, int b) {

		int loopCount = 5;

		long init = System.currentTimeMillis();

		List<Integer> valList = IntStream.rangeClosed(1, loopCount).boxed().map((x) -> invokeService(a + "+" + b, ""))
				.map((x) -> x - 1).map((x) -> invokeService(x + "*" + b, "")).collect(Collectors.toList());

		System.out.println("ͬ��[muitMapByStream]����ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");

		// �������⣬5��ѭ����������2�ι̶���ʱΪ1���ģ���������˳������ʽ�ķ�ʽ�϶���Ҫ10�����ϣ���ʵ��ȷʵ��10424����

		return valList.stream().reduce(Integer::sum).get() / loopCount;
	}

	/**
	 * ���Ը���������ˮ�ߣ�����ʹ��CompletableFuture�����첽�ķ�ʽ�����ṩ������첽����������֧��
	 * 
	 * @param a
	 *            ��Ҫ�����a����
	 * @param b
	 *            ��Ҫ�����b����
	 * @return ���ý����ƽ��ֵ
	 */
	public int muitMapByCompletableFuture(int a, int b) {

		int loopCount = 5;

		// ִ�������������̳߳صĴ�С����Ҳ��Ҫ����һ�����ޣ���ֹ����������
		Executor executor = Executors.newFixedThreadPool(Math.min(loopCount, 50), new ThreadFactory() {

			@Override
			public Thread newThread(Runnable arg0) {
				Thread t = new Thread(arg0);
				// ʹ���ػ��̣߳���֤�����˳�����һ��رմ��߳�
				t.setDaemon(true);
				return t;
			}
		});

		long init = System.currentTimeMillis();

		List<CompletableFuture<Integer>> valList = IntStream.rangeClosed(1, loopCount).boxed()
				.map((x) -> CompletableFuture.supplyAsync(() -> invokeService(a + "+" + b, "�������"), executor))
				.map(future -> future.thenApply(x -> x - 1))
				.map(future -> future.thenCompose(
						x -> CompletableFuture.supplyAsync(() -> invokeService(x + "*" + b, "�������"), executor)))
				.collect(Collectors.toList());

		Optional<Integer> sum = valList.stream().map(f -> f.join()).reduce(Integer::sum);

		System.out.println("�첽����[muitMapByCompletableFuture]����ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");

		// �ܺ�ʱ2399���룬������������һ�·�����ʲô��
		// 1. ����ִ����executor����ͬʱ����5���߳�
		// 2. ʹ��CompletableFuture��supplyAsync���������첽����ģ���Զ�̷���������ӽ��
		// 3. ��ӵĽ����1�����ֲ�����Ҫ���񷵻ؽ�������Ҳ������д�����ʱ�����Դ�������غ�ֱ��ִ�С�Completable��thenApply�����ͻ���supplyAsync���ؽ��������ִ�С�
		// 4. �ڶ�����������ȡ�����Ľ�������b�ĳ˻������Completable��thenCompose����������첽����������ˮ�ߣ�����һ������ʱ�������Ϊ�������ڶ��ε���
		// ���ηֱ�5�ι�10������ÿ�β���ִ��5���̵߳������ڹ̶���ʱ1���ǰ���µ�Ȼֻ��Ҫ2�������ɡ�
		// thenComposeAsync�����ṩthenCompose�Ĳ���ʵ�֣��⽫�����µ��̣߳��������ڵڶ���������Ҫ��һ�εĽ��������û��Ҫʹ�ò���ʵ�֣�����������������ʹ���̵߳Ŀ���

		return sum.get() / loopCount;
	}

	/**
	 * 
	 * ��������ٴθ��ģ���һ�ε��÷��������������ӣ��ڶ��ε��÷��񽫼���������������˵Ľ���������Ҫ�����������ӡ�<br />
	 * ������������û��ǰ������������Ҫ�������첽����Ľ�����������ͬ��ʹ��������ˮ�ߣ�����thenCombine�������Խ�ǰ������CompletableFuture��������������ڶ�����ˮ�߻�ȡ��ϵĽ����
	 * 
	 * @param a
	 *            ��Ҫ�����a����
	 * @param b
	 *            ��Ҫ�����b����
	 * @return ���ý����ƽ��ֵ
	 */
	public int muitMapByCompletableFutureCombine(int a, int b) {

		int loopCount = 5;

		// ִ�������������̳߳صĴ�С����Ҳ��Ҫ����һ�����ޣ���ֹ����������
		Executor executor = Executors.newFixedThreadPool(Math.min(loopCount, 50), new ThreadFactory() {

			@Override
			public Thread newThread(Runnable arg0) {
				Thread t = new Thread(arg0);
				// ʹ���ػ��̣߳���֤�����˳�����һ��رմ��߳�
				t.setDaemon(true);
				return t;
			}
		});
		
		long init = System.currentTimeMillis();

		List<CompletableFuture<Integer>> valList = IntStream.rangeClosed(1, loopCount).boxed()
				.map(x -> CompletableFuture.supplyAsync(() -> invokeService(a + "+" + b, "�������"), executor).thenCombine(
						CompletableFuture.supplyAsync(() -> invokeService(a + "*" + b, "�������"), executor), (c, d) -> c + d))
				.collect(Collectors.toList());

		// thenCombine���ڱ��߳̽��������첽�����������㣬����Ҫʹ��thenCombineAsync�������̡߳�
		// thenCombine�ĵڶ����������൱�ڸ�������δ���صĽ������˼����ʶ����ʹ����join����ʱ�ŵȴ��������������ʱ����ָ�������㡣
		
		Optional<Integer> sum = valList.stream().map(f -> f.join()).reduce(Integer::sum);
		
		System.out.println(
				"�첽����[muitMapByCompletableFutureCombine]����ִ���ܺ�ʱ��" + (System.currentTimeMillis() - init) + "����");

		// �ܺ�ʱ2403���룬ִ��������ͬʱ����5���̣߳������ܺ�ʱҲ���ڴ���һ��
		
		return sum.get() / loopCount;
	}
	
	/**
	 * ����ʹ��thenAcceptָ����������CompletableFuture�������ʱ���н���Ĵ�������CompletableFuture���ṩ��allOf��anyOfָ����һ��CompletableFuture������ȫ�����/�����κ�һ�����ʱ�Ĵ�������<br />
	 * ����ģ���������������Զ������ӿ�ѭ��5�ε��ã���ȫ�����/�����κ�һ����ɺ�ʹ�ӡ�����
	 * @param a
	 * @param b
	 */
	public void completion(int a, int b) {
		
		int loopCount = 5;

		// ִ�������������̳߳صĴ�С����Ҳ��Ҫ����һ�����ޣ���ֹ����������
		Executor executor = Executors.newFixedThreadPool(Math.min(loopCount, 50), new ThreadFactory() {

			@Override
			public Thread newThread(Runnable arg0) {
				Thread t = new Thread(arg0);
				// ʹ���ػ��̣߳���֤�����˳�����һ��رմ��߳�
				t.setDaemon(true);
				return t;
			}
		});
		
		long init = System.currentTimeMillis();
		
		Stream<CompletableFuture<Integer>> stream = IntStream.rangeClosed(1, loopCount).boxed()
				.map(x -> CompletableFuture.supplyAsync(() -> invokeService(a + "+" + b, "�������"), executor).thenCombine(
						CompletableFuture.supplyAsync(() -> invokeService(a + "*" + b, "�������"), executor), (c, d) -> c + d))
				.collect(Collectors.toList()).stream();
		
		// ʹ��thenAccept����������е�CompletableFuture������ɺ�������ӡ���
		CompletableFuture<Integer>[] arr = stream.map(f->f.thenAccept(s->System.out.println("thenAccept������:"+s+" ��ʱ��"+(System.currentTimeMillis()-init))))
			.toArray(size->new CompletableFuture[size]);
		
		// CompletableFuture.allOf(arr).join();
		// allOf������CompletableFuture���������ж���ɺ󷵻�CompletableFuture<Void>���˴���Ϊ�ȴ�����CompletableFuture���󷵻أ�����̨�����5��thenAccept��ָ����������
		
		CompletableFuture.anyOf(arr).join();
		// anyOf�����ز���CompletableFuture�����������ɵĶ��󣨵�Ȼ�������ͬʱ��ɵ�thenAccept��ִ�ж�Σ���ֻ��������һ��CompletableFuture<Object>����
		
	}

	public static void main(String[] args) {

		FutureDemo demo = new FutureDemo();

//		demo.syncProcess(4, 5);
//
//		demo.asyncProcessByFuture(4, 5);
//
//		demo.asyncProcessByCompletableFuture(4, 5);
//
//		demo.asyncProcessByCompletableFutureSupplyAsync(4, 5);
//
//		demo.syncProcessByStream(4, 5);
//
//		demo.asyncProcessByStream(4, 5);
//
//		demo.asyncProcessByCompletableFutureAndStream(4, 5);
//
//		demo.asyncProcessCompare(4, 5);
//
//		demo.withExecutor(4, 5);
//
//		demo.muitMap(4, 5);
//
//		demo.muitMapByCompletableFuture(4, 5);
//
//		demo.muitMapByCompletableFutureCombine(4, 5);
		
		demo.completion(4, 5);
	}
}
