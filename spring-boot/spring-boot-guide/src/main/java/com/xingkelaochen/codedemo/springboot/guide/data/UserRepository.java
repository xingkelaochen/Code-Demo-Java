package com.xingkelaochen.codedemo.springboot.guide.data;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xingkelaochen.codedemo.springboot.guide.data.custom.MyBaseRepository;

/**
 * 
 * ��ע��@Repository��ע�Ľӿڣ�ͨ���̳�JpaRepository�ӿڣ�ӵ�����ڶ�ͨ�õ����ݿ�ֿ�Ĳ������������˽ӿڲ���������ʵ�֣�һ�ж���Spring Data��̬���ɡ�
 *
 * 
 * <p>
 * 	�˽ӿڼ̳����Զ������MyBaseRepository�ӿ�
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
	 * @param name
	 * @return
	 */
	public List<User> findByName(String name);
	
}
