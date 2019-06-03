package com.example.apigateway.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter {

	@Override
	public Object run() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();

		Object accessToken = request.getParameter("accessToken");
		if(accessToken==null){
			requestContext.setSendZuulResponse(false);
			requestContext.setResponseStatusCode(401);
			return null;
		}
		return null;
//		String name = (String)request.getSession().getAttribute("username");
//		name = "wenfeng";
//		requestContext.addZuulRequestHeader("username", "sessionId="+name);
//		requestContext.setSendZuulResponse(true);
//		requestContext.setResponseStatusCode(200);
//		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}
//	public void test(){
//	    throw new RuntimeException("error!!!!");
//    }

}
