package com.xingkelaochen.codedemo.springboot.guide.controller.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.xingkelaochen.codedemo.springboot.guide.controller.DemoController;

/**
 * 
 * 使用@ControllerAdvice增强Controller类，basePackageClasses指定增强类的列表。当捕获到指定的异常类型，进行处理与响应。
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
@ControllerAdvice(basePackageClasses = DemoController.class)
public class DemoControllerAdvice extends ResponseEntityExceptionHandler {

	/**
	 * 当DemoController中的控制方法抛出任何异常时，将向请求者返回ExceptionResponseObject对象，Controller将通过预置的json转换器将此对象转换为json字符串，响应的HttpStatus值为500。
	 * 
	 * <p>注解@ExceptionHandler指定捕获异常的类型</p>
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	ResponseEntity<ExceptionResponseObject> handleControllerExceptionForException(HttpServletRequest request, Throwable ex) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		return new ResponseEntity<ExceptionResponseObject>(new ExceptionResponseObject(ex.toString(), status.value()),status);
	}
}
