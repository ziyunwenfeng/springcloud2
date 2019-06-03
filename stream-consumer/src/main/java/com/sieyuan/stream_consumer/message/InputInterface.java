package com.sieyuan.stream_consumer.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface InputInterface {
    String INPUT1 = "input1";
    String INPUT2 = "input2";
    @Input(INPUT1)
    SubscribableChannel input1();
    @Input(INPUT2)
    SubscribableChannel input2();
}
