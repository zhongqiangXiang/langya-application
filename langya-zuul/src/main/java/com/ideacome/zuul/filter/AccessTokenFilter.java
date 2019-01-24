package com.ideacome.zuul.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ideacome.security.properties.SecurityConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ServletInputStreamWrapper;

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

	private List<String> uriExcluded = Stream.of(SecurityConstants.DEFAULT_LOGIN_PAGE_URL).collect(Collectors.toList());

	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();

		// 获取请求参数name
		String token = "";
		// 请求方法
		String method = request.getMethod();
		log.info(String.format("%s >>> %s", method, request.getRequestURL().toString()));

		// 获取请求的输入流
		InputStream in = null;
		try {
			// get方法和post、put方法处理方式不同
			if ("GET".equals(method) || ("POST".equals(method) && !"application/json".equals(request.getContentType()))) {
				token = request.getParameter("token");
				if (StringUtils.isNotEmpty(token)) {
					log.info("AccessTokenFilter-token:{}",token);
					// 关键步骤，一定要get一下,下面才能取到值requestQueryParams
					request.getParameterMap();
					Map<String, List<String>> requestQueryParams = currentContext.getRequestQueryParams();
					if (requestQueryParams == null) {
						requestQueryParams = new HashMap<>();
					}
					currentContext.setRequestQueryParams(requestQueryParams);
					
					return null;
				}
			} else if ("POST".equals(method) || "PUT".equals(method)) {
				if("application/json".equals(request.getContentType())){
					in = request.getInputStream();
					String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
					// 如果body为空初始化为空json
					if (StringUtils.isBlank(body)) {
						body = "{}";
					}
					log.info("body{}", body);
					// 转化成json
					JSONObject json = JSON.parseObject(body);
					
					token = json.getString("token");
					if (StringUtils.isNotBlank(token)) {
						log.info("AccessTokenFilter-token:{}",token);
						final byte[] reqBodyBytes = json.toString().getBytes(Charset.forName("UTF-8"));
						// 重写上下文的HttpServletRequestWrapper
						currentContext.setRequest(new HttpServletRequestWrapper(request) {
							@Override
							public ServletInputStream getInputStream() throws IOException {
								return new ServletInputStreamWrapper(reqBodyBytes);
							}
							@Override
							public int getContentLength() {
								return reqBodyBytes.length;
							}
							@Override
							public long getContentLengthLong() {
								return reqBodyBytes.length;
							}
						});
						
						return null;
					}
				}else{
					log.warn("AccessFilter过滤，request 不支持form-data方式提交");
					//TO-DO
				}
			}
			log.warn("AccessFilter过滤，request 中没有带 token");
			
			currentContext.setSendZuulResponse(false);
			currentContext.setResponseStatusCode(401);
			currentContext.set("isSuccess", false);
			
			Map<String, Object> res = new HashMap<>();
			res.put("code", 9999);
			res.put("msg", "错误！！！");
			currentContext.setResponseBody(JSON.toJSONString(res));
			currentContext.getResponse().setContentType("application/json; charset=utf-8");
			
		} catch (IOException e) {
			e.printStackTrace();
			log.error("AccessTokenFilter 错误信息：{}", e.getMessage());
			
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;

	}

	@Override
	public boolean shouldFilter() {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();

		return ! uriExcluded.contains(request.getRequestURI());
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
