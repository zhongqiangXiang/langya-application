package com.ideacome.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ideacome.security.dto.BaseResponse;
import com.ideacome.security.properties.SecurityProperties;
import com.ideacome.security.properties.enums.LoginResponseType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("authenticationFailedHandler")
public class AuthenticationFailedHandler extends SimpleUrlAuthenticationFailureHandler {

	private SecurityProperties securityProperties=new SecurityProperties();
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		log.info("登录失败:" + e.getMessage());
        log.info("username=>" + request.getParameter("username"));
		// 设置状态码
		response.setStatus(500);
		// 将 登录失败 信息打包成json格式返回
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(e));
		if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(JSON.toJSONString(new BaseResponse(e.getMessage())));
		}else{
			super.onAuthenticationFailure(request, response, e);
		}

	}
}
