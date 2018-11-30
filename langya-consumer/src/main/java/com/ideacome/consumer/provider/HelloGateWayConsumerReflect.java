package com.ideacome.consumer.provider;

import org.springframework.cloud.openfeign.FeignClient;

import com.ideacome.consumer.config.FeignConfig;
import com.ideacome.services.api.GreetingControllerInterface;

@FeignClient(value="langya-services",configuration=FeignConfig.class)
public interface HelloGateWayConsumerReflect extends GreetingControllerInterface{
}
