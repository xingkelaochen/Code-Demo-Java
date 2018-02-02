package com.xingkelaochen.javademo.springcloud.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 
 * �Զ����Zuul���ع�����
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
	 * ���������ͣ�������������������ĸ���������ִ��
	 * 
	 * <p>
	 * 	pre: ����·��֮ǰ
	 * 	routing: ����·��ʱ
	 * 	post: ��routing��error֮��
	 * 	error: ��������������ʱ
	 * </p>
	 * 
	 */
	@Override
	public String filterType() {
		
		return "pre";
	}
	
	/**
	 * �Ƿ��������й���
	 */
	@Override
	public boolean shouldFilter() {

		return true;
	}

	/**
	 * ���������������ã���ֵԽС���ȼ�Խ��
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * �������ľ����߼�ʵ��
	 */
	@Override
	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		String token = request.getParameter("token");
		
		// ���ˣ���������в���token���ԣ��򲻽���ZUUL·��ֱ�ӷ����쳣��
		if(token==null||token.isEmpty()){
			
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
			ctx.setResponseBody("token error");
		}
		
		return null;
	}

}
