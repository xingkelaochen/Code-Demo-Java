package com.xingkelaochen.codedemo.java.java8.optional;

import java.util.Optional;

/**
 * ��ʾOptional������÷�
 * <p>
 * 	����ģ��һ���û�-����-��ʻ֤�Ĺ���ϵͳ�����г�������ʻ֤���п���Ϊ�ա�
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
public class OptionalDemo {
	
	/**
	 * ָ��ʽ��̷�ʽ����ȡ�����û���ӵ�г�������ʻ֤
	 * 
	 * <p>
	 * �û��ĳ�������Ϊ�գ���������ʻ֤Ҳ����Ϊ�գ����û����ʻ֤�����շ���null���⽫�������������⣺
	 * <ul>
	 * <li>���ò���null����if/else�ж�</li>
	 * <li>�˷��������п��ܷ��ص�null�����µ��÷���Ҳ�ý������Ʒ������жϣ����ҿ��ܵ��²������ϵ�NullPointException����</li>
	 * </ul>
	 * </p>
	 * @param users
	 */
	public DrivingLicense getDrivingLicense(User user){
			
		Car car = user.getCar();
			
		if(car!=null) {
			return car.getDrivingLicense();
		}else {
			return null;
		}
		
	}
	
	/**
	 * ���ϱߵķ���ʹ��Optional���и���(User��Car�����ж��ṩ��Optional�Ļ�ȡ����)
	 * 
	 * <p>
	 * 	���ص�Optional<DrivingLicense>������ȷ�ĸ�֪�˵����߽������Ϊ�գ�����ʵ���ǻ�������ҩ�����Ǳ��ⲻ�˸����ж�
	 * </p>
	 * 
	 * @param user
	 * @return
	 */
	public Optional<DrivingLicense> getDrivingLicenseByOptional(User user) {
		
		Optional<Car> car = user.getCarAsOptional();
		
		if(car.isPresent()) {
			return car.get().getDrivingLicenseAsOpitonal();
		}else {
			return Optional.empty();
		}
		
	}
	
	/**
	 * OptionalҲ֧����Streamһ����mapӳ�����
	 * 
	 * @param user
	 * @return
	 */
	public Optional<DrivingLicense> getDrivingLicenseByOptionalAndMap(User user) {
		
		Optional<User> userOptional = Optional.of(user);
		
		// Optional����ʹ��map����ӳ�䣬������Optional��װ�Ķ����ڴ˴�flatMap(User::getCarAsOptional)������Optional<Optional<Car>>��������Ҫʹ��flatMap��ȡOptional<Car>����
		return userOptional.flatMap(User::getCarAsOptional).flatMap(Car::getDrivingLicenseAsOpitonal);
		
	}
	
	public static void main(String[] args) {
		
		OptionalDemo optionalDemo = new OptionalDemo();
		
		User user = new User();
		
		Optional<DrivingLicense> drivingLicenseOptional = optionalDemo.getDrivingLicenseByOptionalAndMap(user);
		
		// orElse ���Ϊ�գ���ֵ
		DrivingLicense drivingLicense = drivingLicenseOptional.orElse(new DrivingLicense("none"));
		
		System.out.println(drivingLicense.getCode());
	}
}

/**
 * �û�����
 *
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
class User{
	
	// ���ɿ�
	private String name;
	
	// ����Ϊ��
	private Car car;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	/**
	 * Optional�����ܱ����л���������õ����ṩһ��Optional�Ļ�ȡ����
	 * @return
	 */
	public Optional<Car> getCarAsOptional(){
		return Optional.ofNullable(getCar());
	}
	
}
/**
 * 
 * ��������
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
class Car{
	
	// ���ɿ�
	private String number;
	
	private DrivingLicense drivingLicense;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public DrivingLicense getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(DrivingLicense drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	
	/**
	 * Optional�����ܱ����л���������õ����ṩһ��Optional�Ļ�ȡ����
	 * @return
	 */
	public Optional<DrivingLicense> getDrivingLicenseAsOpitonal() {
		return Optional.ofNullable(getDrivingLicense());
	}
}

/**
 * ��ʻ֤
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
class DrivingLicense{
	
	// ���ɿ�
	private String code;
	
	public DrivingLicense(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}