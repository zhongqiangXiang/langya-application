package com.ideacome.services.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideacome.services.vo.ResultVO;

public interface InsUserExtServiceControllerInterface {
	@RequestMapping("/getInsUserExt")
	public ResultVO getInsUserExtSelective(@RequestParam(value="userId")Long userId);
}
