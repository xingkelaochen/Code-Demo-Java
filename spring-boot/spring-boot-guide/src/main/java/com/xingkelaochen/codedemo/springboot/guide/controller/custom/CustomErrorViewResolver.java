package com.xingkelaochen.codedemo.springboot.guide.controller.custom;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * �Զ������ҳ�����
 * 
 * <p>
 * 	Ĭ�ϵģ����controller�׳��쳣���쳣��������������ھ�̬Ŀ¼��Ѱ����httpStatus���Ӧ��error/xxx.htmlҳ�棨���ʹ��ģ�壬����templates/error/��Ѱ�ң������쳣��Ӧ����ʾ��
 *  ����Ҳ����ͨ����дresolveErrorView���ж��ƻ���
 * </p>
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
@Component
public class CustomErrorViewResolver implements ErrorViewResolver {

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {

		ModelAndView modelAndView = new ModelAndView();
		
		if(status.value()==HttpStatus.NOT_FOUND.value()) {
			// ����������Ϊ404������ת��ָ����·��
			
			modelAndView.addObject("info", "exception: "+status.value());
			modelAndView.setViewName("error/exception");
			modelAndView.setStatus(status);
		}
		
		return modelAndView;
		
	}

}
