package com.ideacome.consumer.consumeService;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.ideacome.consumer.config.FeignConfig;
import com.ideacome.services.api.GreetingControllerInterface;

@FeignClient(value="langya-services",configuration=FeignConfig.class)
public interface HelloGateWayConsumerReflect extends GreetingControllerInterface{
}
