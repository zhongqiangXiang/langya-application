package com.ideacome.consumer.consumeService;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.ideacome.consumer.config.FeignConfig;
import com.ideacome.services.api.InsUserExtServiceControllerInterface;

@FeignClient(value="langya-services",configuration=FeignConfig.class)
public interface InsUserExtGateWayReflect extends InsUserExtServiceControllerInterface {
}
