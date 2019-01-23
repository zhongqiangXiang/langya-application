package com.ideacome.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ideacome.security.properties.SecurityConstants;
import com.ideacome.security.service.UserDetailSecurityService;
import com.ideacome.security.vo.MD5PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //  启用方法级别的权限认证
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailSecurityService userDetailSecurityService;
	@Autowired
	private AuthenticationFailedHandler authenticationFailedHandler;
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailSecurityService).passwordEncoder(getEncryPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		applyPasswordAuthenticationConfig(http);


	}

	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
				.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)                      // 登录页面回调
				.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)        // 自定义的登录接口
				.successHandler(authenticationSuccessHandler)                                 // 认证成功回调
				.failureHandler(authenticationFailedHandler);                                // 认证失败回调
	}
	
	@Bean("passwordEncoder")
	public PasswordEncoder getEncryPasswordEncoder(){
		return new MD5PasswordEncoder();
	}
	
}
