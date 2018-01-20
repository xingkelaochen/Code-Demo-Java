package com.xingkelaochen.codedemo.springboot.guide.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.xingkelaochen.codedemo.springboot.guide.data.custom.MyBaseRepository;

/**
 * 
 * ��ע��@Repository��ע�Ľӿڣ�ͨ���̳�JpaRepository�ӿڣ�ӵ�����ڶ�ͨ�õ����ݿ�ֿ�Ĳ������������˽ӿڲ���������ʵ�֣�һ�ж���Spring Data��̬���ɡ�
 *
 * 
 * <p>
 * 	�˽ӿڼ̳����Զ������MyBaseRepository�ӿڣ�ָ����JpaRepository��ʵ��ΪMyBaseRepositoryImpl��
 * </p>
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
@Repository
public interface UserRepository extends MyBaseRepository<User, String>{

	/**
	 * ����Repository��findByXxx��������ʹ��name���в�ѯ�ķ���
	 * 
	 * ע��@Nullable��@NonNull�������ڷ���������ϣ�У��Ŀ���Ƿ����Ϊnull��@NonNullApiע�����ڰ������package-info.java�ļ��У����ɷ��������@Nullable��@NonNull���ǡ�
	 * 
	 * @param name
	 * @return
	 */
	@Nullable
	public List<User> findByName(@NonNull String name);
	
	/**
	 * ��ѯ�û�(User)��ϵ��Ϣ(ContactInfo)�ĵ�ַ���ڴ���������б�Ϊ����������ʹ��_�������������
	 * @param address
	 * @return
	 */
	public List<User> findByContactInfo_Address(String address);
	
	// ����Լ�����������ݲ�������
	
	/**
	 * findByXxxAndXxx���򣬲�ѯ����������Ϊ�������û��б�
	 * 
	 * @param name
	 * @param sex
	 * @return
	 */
	public List<User> findByNameAndSex(String name,String sex);
	
	/**
	 * findDistinctByXxxOrXxx���򣬲�ѯ��ָ������Ϊ���򡱹�ϵ�����������Ҳ���ͬ���û��б�
	 * @param name
	 * @param sex
	 * @return
	 */
	public List<User> findDistinctUserByNameOrSex(String name,String sex);
	
	/**
	 * findByXxxOrderByXxxAsc/Desc���������ѯ�û��б�
	 * @param age
	 * @return
	 */
	public List<User> findByAgeOrderByAgeAsc(int age);
	
	/**
	 * ����Pageable��ҳ���������з�ҳ��ѯ
	 * Page�ĸ�����Slice
	 * 
	 * @param name
	 * @param pageable
	 * @return
	 */
	public Page<User> findByName(String name,Pageable pageable);
	
	/**
	 * ����Pageable��ҳ���������з�ҳ��ѯ
	 * Slice��Page�ĸ���
	 * 
	 * @param name
	 * @param pageable
	 * @return
	 */
	 // public Slice<User> findByName(String name,Pageable pageable);
	
	/**
	 * ����Sort���������ѯ�����û��б�
	 * @param name
	 * @param sort
	 * @return
	 */
	public List<User> findByName(String name,Sort sort);
	
	/**
	 * findFirstByXxxOrderByXxxDesc����ѯ���������û�
	 * First��Top����ͬ������
	 * @return
	 */
	public User findFirstByOrderByAgeDesc();
	
	/**
	 * ��ѯ������С���û�
	 * @return
	 */
	public User findTopByOrderByAgeAsc();
	
	/**
	 * ��ѯ������С��ǰʮλ
	 * 
	 * @return
	 */
	public List<User> findTop10ByOrderByAgeAsc();
	
	/**
	 * NotNull�����޶�������Java8֧�ֵ�Stream
	 * @return
	 */
	public Stream<User> findByNameNotNull();
	
	/**
	 * /**
	 * ʹ��ע��@Query��ע�ķ�������ʹ��Ĭ�Ϲ�����ͣ���ʹ�ø����������������ѯ��������ʹ��?1�����
	 * <p>
	 * nativeQueryΪtrue�Ļ�����ʹ��ԭ��̬��SQL���
	 * �����ʹ��#{#entityName}���ʽ����ʹ��Repository����Entity��value���ԣ��˴���User
	 * </p>
	 * 
	 * @param name
	 * @param sort
	 * @return
	 */
	@Query(value="select u from #{#entityName} u where u.name like %?1%",nativeQuery=false)
	public Stream<User> findByCustomQueryAndStream(String name,Sort sort);
	
	/**
	 * ʹ��ע��@Async��ע�������첽ִ�ж���֧��Future��CompletableFuture��ListenableFuture
	 * @param name
	 * @return
	 */
	@Async
	public CompletableFuture<User> findAsyncByName(String name);
}
