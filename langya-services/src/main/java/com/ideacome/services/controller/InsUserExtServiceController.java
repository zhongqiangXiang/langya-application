package com.ideacome.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ideacome.services.api.InsUserExtServiceControllerInterface;
import com.ideacome.services.bizService.InsUserExtService;
import com.ideacome.services.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class InsUserExtServiceController implements InsUserExtServiceControllerInterface{
	@Autowired
	private InsUserExtService insUserExtServiceImpl;
	
	@Override
	public ResultVO getInsUserExtSelective(Long userId){
		log.info("InsUserExtServiceController.getInsUserExtSelective：userId-{}",userId);
		
		return ResultVO.newSuccess(10000, "success", insUserExtServiceImpl.selectInsUserExtSelective(userId));
	}
}
