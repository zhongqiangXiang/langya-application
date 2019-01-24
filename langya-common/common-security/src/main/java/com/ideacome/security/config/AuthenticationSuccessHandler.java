package com.ideacome.security.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ideacome.security.properties.SecurityProperties;
import com.ideacome.security.properties.enums.LoginResponseType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        log.info("username=>" + request.getParameter("username"));

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
        	Map<String, Object> map = new HashMap<>();
            map.put("code","0");
            map.put("msg","登录成功");
            map.put("data",authentication);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(map));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
