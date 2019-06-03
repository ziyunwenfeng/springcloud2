package com.sieyuan.consumer.Controller;

import org.springframework.stereotype.Component;

@Component
public class ConsumerFailure implements ConsumerInterface{
    public String consumer(){
        return "consumer failure";
    }
}
