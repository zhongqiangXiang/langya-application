package com.ideacome.services.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ideacome.base.res.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * 捕获系统所有的异常
 * @author Administrator
 *
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result<?> exceptionHandler(HttpServletRequest req, Exception e){
		log.error("GlobalExceptionHandler.exceptionHandler:Url-{},errorMsg-{}",req.getRequestURI(),e.getMessage());
		e.printStackTrace();//输出错误信息
		
		return Result.newFailure("系统异常，请稍后再试。。。");
	}
}
