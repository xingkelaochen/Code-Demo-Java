package com.xingkelaochen.javademo.springcloud.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 
 * 自定义的Zuul网关过滤器
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
public class CustomFilter extends ZuulFilter {

	/**
	 * 过滤器类型，决定过滤器在请求的哪个生命周期执行
	 * 
	 * <p>
	 * 	pre: 请求被路由之前
	 * 	routing: 请求被路由时
	 * 	post: 在routing与error之后
	 * 	error: 处理请求发生错误时
	 * </p>
	 * 
	 */
	@Override
	public String filterType() {
		
		return "pre";
	}
	
	/**
	 * 是否对请求进行过滤
	 */
	@Override
	public boolean shouldFilter() {

		return true;
	}

	/**
	 * 过滤器的排序设置，数值越小优先级越高
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 过滤器的具体逻辑实现
	 */
	@Override
	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		String token = request.getParameter("token");
		
		// 过滤，如果请求中不带token属性，则不进行ZUUL路由直接返回异常。
		if(token==null||token.isEmpty()){
			
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
			ctx.setResponseBody("token error");
		}
		
		return null;
	}

}
