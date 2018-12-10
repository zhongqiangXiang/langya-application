package com.ideacome.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideacome.base.enums.ErrorEnum;
import com.ideacome.consumer.consumeService.InsUserExtGateWayReflect;
import com.ideacome.consumer.vo.InsUserExtVO;
import com.ideacome.services.vo.ResultVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class InsUserExtGateWayController{
	@Autowired
	private InsUserExtGateWayReflect insUserExtGateWayReflect;
	
	@HystrixCommand(fallbackMethod = "getInsUserExtBackUp", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	@RequestMapping("/getInsUserExt")
	public ResultVO getInsUserExt(@RequestBody InsUserExtVO insUserExtVO) {
		log.info("receive param:userId-{}", insUserExtVO.getUserId());
		return insUserExtGateWayReflect.getInsUserExtSelective(insUserExtVO.getUserId());
	}

	public ResultVO getInsUserExtBackUp(@RequestBody InsUserExtVO insUserExtVO) {

		return ResultVO.newFailure(ErrorEnum.failure.getErrorCode(), "userId:"+insUserExtVO.getUserId()+",失败："+ErrorEnum.failure.getErrorMSG());
	}
	
}
