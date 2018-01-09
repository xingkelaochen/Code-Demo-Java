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
 * ʹ��@ControllerAdvice��ǿController�࣬basePackageClassesָ����ǿ����б�������ָ�����쳣���ͣ����д�������Ӧ��
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
	 * ��DemoController�еĿ��Ʒ����׳��κ��쳣ʱ�����������߷���ExceptionResponseObject����Controller��ͨ��Ԥ�õ�jsonת�������˶���ת��Ϊjson�ַ�������Ӧ��HttpStatusֵΪ500��
	 * 
	 * <p>ע��@ExceptionHandlerָ�������쳣������</p>
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
