package com.xingkelaochen.codedemo.java.java8.optional;

import java.util.Optional;

/**
 * 演示Optional对象的用法
 * <p>
 * 	本例模拟一个用户-车辆-行驶证的关联系统，其中车辆与行驶证都有可能为空。
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
	 * 指令式编程方式，获取给定用户所拥有车辆的行驶证
	 * 
	 * <p>
	 * 用户的车辆可能为空，车辆的行驶证也可能为空，如果没有行驶证将最终返回null。这将有以下两种问题：
	 * <ul>
	 * <li>不得不对null进行if/else判断</li>
	 * <li>此方法最终有可能返回的null，导致调用方法也得进行类似繁琐的判断，并且可能导致不可意料的NullPointException发生</li>
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
	 * 将上边的方法使用Optional进行改造(User、Car对象中都提供了Optional的获取方法)
	 * 
	 * <p>
	 * 	返回的Optional<DrivingLicense>对象明确的告知了调用者结果可能为空，但其实还是换汤不换药，还是避免不了各种判断
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
	 * Optional也支持与Stream一样的map映射操作
	 * 
	 * @param user
	 * @return
	 */
	public Optional<DrivingLicense> getDrivingLicenseByOptionalAndMap(User user) {
		
		Optional<User> userOptional = Optional.of(user);
		
		// Optional对象使用map进行映射，其结果是Optional包装的对象，在此处flatMap(User::getCarAsOptional)将返回Optional<Optional<Car>>，所以需要使用flatMap获取Optional<Car>对象。
		return userOptional.flatMap(User::getCarAsOptional).flatMap(Car::getDrivingLicenseAsOpitonal);
		
	}
	
	public static void main(String[] args) {
		
		OptionalDemo optionalDemo = new OptionalDemo();
		
		User user = new User();
		
		Optional<DrivingLicense> drivingLicenseOptional = optionalDemo.getDrivingLicenseByOptionalAndMap(user);
		
		// orElse 如果为空，则赋值
		DrivingLicense drivingLicense = drivingLicenseOptional.orElse(new DrivingLicense("none"));
		
		System.out.println(drivingLicense.getCode());
	}
}

/**
 * 用户对象
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
	
	// 不可空
	private String name;
	
	// 可能为空
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
	 * Optional对象不能被序列化，所以最好单独提供一个Optional的获取方法
	 * @return
	 */
	public Optional<Car> getCarAsOptional(){
		return Optional.ofNullable(getCar());
	}
	
}
/**
 * 
 * 汽车对象
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
	
	// 不可空
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
	 * Optional对象不能被序列化，所以最好单独提供一个Optional的获取方法
	 * @return
	 */
	public Optional<DrivingLicense> getDrivingLicenseAsOpitonal() {
		return Optional.ofNullable(getDrivingLicense());
	}
}

/**
 * 行驶证
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
	
	// 不可空
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