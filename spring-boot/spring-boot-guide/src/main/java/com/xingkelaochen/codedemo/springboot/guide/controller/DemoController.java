package com.xingkelaochen.codedemo.springboot.guide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ����Ŀʾ��ʹ��ThymeleafViewResolver������Thymeleafģ���ļ�������Ӧ��Ⱦ
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
@Controller
@RequestMapping(name="/")
public class DemoController {
	
	@RequestMapping(value="hello",method= {RequestMethod.GET,RequestMethod.POST})
	public String viewTemplate() {
		return "hello";
	}
	
}
