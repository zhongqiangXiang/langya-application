package com.ideacome.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideacome.base.res.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthRedirectController {
	
	@RequestMapping("/auth/loginRemind")
	public Result<?> loginRemind(){
		log.info("AuthRedirectController.loginRemind：登陆失败");
		
		Map<String,Object> result = new HashMap<>();
		result.put("code", 403);
		result.put("msg", "请重新登陆！");
		result.put("value", "/auth/form");//登陆地址
		
		return Result.newSuccess(result);
	}
	@RequestMapping("/auth/form")
	public Result<?> loginForm(String username,String password){
		log.info("AuthRedirectController.loginForm:参数-username {},password {}",username,password);
		
		Map<String,Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("msg", "登陆成功");
		result.put("value", "成功跳转页面地址：(待添加)");//成功跳转页面地址
		
		return Result.newSuccess(result);
	}
	@RequestMapping("/auth/logout")
	public Result<?> loginOut(){
		log.info("AuthRedirectController.loginOut：退出登陆成功！");
		
		return Result.newSuccess();
	}
	@RequestMapping("/")
	public Result<?> index(){
		return Result.newSuccess();
	}
}
