package com.ideacome.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideacome.base.res.Result;
import com.ideacome.consumer.provider.HelloGateWayConsumerReflect;
import com.ideacome.services.vo.GreetingVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloGateWayController {

	@Autowired
	private HelloGateWayConsumerReflect helloGateWayConsumerReflect;

	@HystrixCommand(fallbackMethod = "helloFailBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	@RequestMapping("/hello")
	public Result<GreetingVO> greeting(String name) {
		log.info("receive param:name-{}", name);
		return helloGateWayConsumerReflect.greeting(name);
	}

	public Result<GreetingVO> helloFailBack(String name) {

		return Result.newSuccess(new GreetingVO(1, "error" + name));
	}
}
