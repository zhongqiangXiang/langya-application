package com.ideacome.zuul.filter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
/**
 * 这个类专门处理请求的 权限过滤操作
 * @author zhuting
 *
 */
public class AccessFilter extends ZuulFilter{
	
	private List<String> uriExcluded = Stream.of("/langya/hello","/langya/login")
										.collect(Collectors.toList());

	@Override
	public Object run() throws ZuulException {
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		
		return (! uriExcluded.contains(request.getRequestURI())) ;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
