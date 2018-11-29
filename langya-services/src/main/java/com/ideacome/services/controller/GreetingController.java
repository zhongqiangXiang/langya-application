package com.ideacome.services.controller;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideacome.services.api.GreetingControllerInterface;
import com.ideacome.services.vo.GreetingVO;

@RestController
public class GreetingController implements GreetingControllerInterface{

	private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);
	
    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Override
    public GreetingVO greeting(@RequestParam(value="name", defaultValue="World") String name,HttpServletRequest request) {
    	LOGGER.info("get visitor");
    	
        return new GreetingVO(counter.incrementAndGet(),
                            String.format(TEMPLATE, name));
    }
}
