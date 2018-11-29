package com.ideacome.zuul.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideacome.services.vo.GreetingVO;
import com.ideacome.zuul.consumer.HelloGateWayConsumerReflect;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloGateWayController{
	
	@Autowired
	private HelloGateWayConsumerReflect helloGateWayConsumerReflect;
	
	@HystrixCommand(fallbackMethod="helloFailBack")
	@RequestMapping("/hello")
	public GreetingVO greeting(String name,HttpServletRequest request) {
		
		
		return helloGateWayConsumerReflect.greeting(name,request);
	}
	
	
	public GreetingVO helloFailBack(String name){
		return new GreetingVO(1,"error"+name);
	}
}
