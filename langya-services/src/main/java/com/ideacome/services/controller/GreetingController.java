package com.ideacome.services.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideacome.base.res.Result;
import com.ideacome.services.api.GreetingControllerInterface;
import com.ideacome.services.vo.GreetingVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GreetingController implements GreetingControllerInterface{

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Override
    public Result<GreetingVO> greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	log.info("get visitor");
    	
        return Result.newSuccess(new GreetingVO(counter.incrementAndGet(),
                            String.format(TEMPLATE, name)));
    }
}
