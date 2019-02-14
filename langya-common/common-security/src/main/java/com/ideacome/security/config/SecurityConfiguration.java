package com.ideacome.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.ideacome.security.config.session.MyExpiredSessionStrategy;
import com.ideacome.security.config.session.MyInvalidSessionStrategy;
import com.ideacome.security.properties.SecurityConstants;
import com.ideacome.security.properties.SecurityProperties;
import com.ideacome.security.service.UserDetailSecurityService;
import com.ideacome.security.vo.MD5PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法级别的权限认证
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailSecurityService userDetailSecurityService;
	@Autowired
	private AuthenticationFailedHandler authenticationFailedHandler;
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	private SecurityProperties securityProperties=new SecurityProperties();

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailSecurityService).passwordEncoder(getEncryPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		applyAuthenticationConfig(http);

	}

	protected void applyAuthenticationConfig(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(SecurityConstants.DEFAULT_LOGIN_PAGE_URL, "/authentication/*").permitAll()
				.antMatchers("/provider/*","/consumer/*").hasAnyRole("loginTest")
				.anyRequest().authenticated().and().formLogin()
				.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) // 登录页面回调
				.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM) // 自定义的登录接口
				.successHandler(authenticationSuccessHandler) // 认证成功回调
				.failureHandler(authenticationFailedHandler) // 认证失败回调
				.and().sessionManagement().invalidSessionStrategy(getInvalidSessionStrategy()) // session超时跳转
				.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions()) // 最大并发session
				.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin()) // 是否阻止新的登录
				.expiredSessionStrategy(getSessionInformationExpiredStrategy()) // 并发session失效原因
		;
		http.csrf().disable();
	}

	@Bean("passwordEncoder")
	public PasswordEncoder getEncryPasswordEncoder() {
		return new MD5PasswordEncoder();
	}

	@Bean("invalidSessionStrategy")
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy getInvalidSessionStrategy() {
		return new MyInvalidSessionStrategy(SecurityConstants.DEFAULT_SESSION_INVALID_URL);
	}

	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy getSessionInformationExpiredStrategy() {
		return new MyExpiredSessionStrategy(SecurityConstants.DEFAULT_SESSION_INVALID_URL);
	}
}
