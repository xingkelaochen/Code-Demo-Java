package com.xingkelaochen.codedemo.springboot.guide.controller.custom;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义错误页面解析
 * 
 * <p>
 * 	默认的，如果controller抛出异常，异常处理解析器会在静态目录下寻找与httpStatus相对应的error/xxx.html页面
 * （如果使用freemarker模板，则在templates/error/下寻找）用于异常响应的显示，但是也可以通过重写resolveErrorView进行定制化。
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
public class CustomErrorViewResolver implements ErrorViewResolver {

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {

		ModelAndView modelAndView = new ModelAndView();
		
		if(status.value()==HttpStatus.NOT_FOUND.value()) {
			// 如果错误代码为404，则跳转到指定的路径
			
			modelAndView.setViewName("redirect:/index");
		}
		
		return modelAndView;
		
	}

}
