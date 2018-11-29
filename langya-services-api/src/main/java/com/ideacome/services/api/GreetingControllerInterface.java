package com.ideacome.services.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideacome.services.vo.GreetingVO;

public interface GreetingControllerInterface {
	
	@RequestMapping("/greeting")
    public GreetingVO greeting(@RequestParam(value="name", defaultValue="World") String name,HttpServletRequest request);
}
