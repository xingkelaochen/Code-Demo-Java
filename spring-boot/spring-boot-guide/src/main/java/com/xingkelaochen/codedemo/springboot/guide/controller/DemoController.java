package com.xingkelaochen.codedemo.springboot.guide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 本项目示例使用ThymeleafViewResolver来解析Thymeleaf模板文件进行响应渲染
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
