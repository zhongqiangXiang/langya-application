package com.ideacome.zuul.consumer;

import org.springframework.cloud.openfeign.FeignClient;

import com.ideacome.services.api.GreetingControllerInterface;
import com.ideacome.zuul.config.FeignConfig;

@FeignClient(value="LANGYA-SERVICES",configuration=FeignConfig.class)
public interface HelloGateWayConsumerReflect extends GreetingControllerInterface{
}
