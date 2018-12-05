package com.ideacome.services.api;

import org.springframework.web.bind.annotation.RequestMapping;

import com.ideacome.services.vo.ResultVO;

public interface InsUserExtServiceControllerInterface {
	@RequestMapping("/getInsUserExt")
	public ResultVO getInsUserExtSelective(Long userId);
}
