package com.sieyuan.consumer.Controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "producer",fallback = ConsumerFailure.class)
public interface ConsumerInterface {
    @RequestMapping(value="/consumer",method= RequestMethod.GET)
    public String consumer();
}
