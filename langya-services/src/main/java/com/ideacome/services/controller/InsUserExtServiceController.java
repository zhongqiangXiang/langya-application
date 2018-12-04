package com.ideacome.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideacome.services.bizService.InsUserExtService;
import com.ideacome.services.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/insUserExt")
public class InsUserExtServiceController {
	@Autowired
	private InsUserExtService insUserExtServiceImpl;
	
	@RequestMapping(name="/getInsUserExt")
	public ResultVO getInsUserExtSelective(Long userId){
		log.info("InsUserExtServiceController.getInsUserExtSelective：");
		
		return ResultVO.newSuccess(10000, "success", insUserExtServiceImpl.selectInsUserExtSelective(userId));
	}
}
