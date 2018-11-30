package com.ideacome.zuul.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

/**
 * 这个类专门处理请求的 权限过滤操作
 * 
 * @author zhuting
 *
 */
@Slf4j
@Component
public class AccessTokenFilter extends ZuulFilter {

	private List<String> uriExcluded = Stream.of("/hello", "/login").collect(Collectors.toList());

	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		
		String tokenParameter = request.getParameter("token");
		String tokenHeader = request.getHeader("token");
		String tokenAttribute = request.getAttribute("token")==null ? null : String.valueOf(request.getAttribute("token"));
		String token = StringUtils.isNotBlank(tokenParameter) ? 
							tokenParameter : StringUtils.isNotBlank(tokenHeader) ? 
									tokenHeader : StringUtils.isNotBlank(tokenAttribute) ? tokenAttribute : null;
		if(StringUtils.isEmpty(token)){
			log.warn("AccessFilter过滤，request 中没有带 token");
			
			currentContext.setSendZuulResponse(false);
			currentContext.setResponseStatusCode(401);
			currentContext.set("isSuccess", false);
			
			Map<String,Object> res = new HashMap<>();
			res.put("code", 9999);
			res.put("msg", "错误！！！");
			currentContext.setResponseBody(JSON.toJSONString(res));
			currentContext.getResponse().setContentType("application/json; charset=utf-8");
			
			return null;
		}
		
		log.info("AccessFilter过滤，token :{}",token);
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext currentContext = RequestContext.getCurrentContext();

		return currentContext.get("isSuccess") == null ? true : (boolean)currentContext.get("isSuccess");
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
