package com.sieyuan.stream_producer.message;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface OutputInterface {
    String OUTPUT1 = "output1";
    String OUTPUT2 = "output2";
    @Output(OUTPUT1)
    MessageChannel output1();
    @Output(OUTPUT2)
    MessageChannel output2();
}
