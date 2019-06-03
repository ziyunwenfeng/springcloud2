package com.sieyuan.stream_producer.service;

import com.sieyuan.stream_producer.entity.Person;
import com.sieyuan.stream_producer.message.OutputInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProducerStreamService {
    @Qualifier(OutputInterface.OUTPUT1)
    @Autowired
    MessageChannel channel1;

    @Qualifier(OutputInterface.OUTPUT2)
    @Autowired
    MessageChannel channel2;

    public void sendMessage(String  channel){
        String message = "message from : "+ channel;
        System.out.println(message);
        switch (channel){
            case OutputInterface.OUTPUT1:
                channel1.send(MessageBuilder.withPayload(message).build());
                break;
            case OutputInterface.OUTPUT2:
                Person person = new Person("web");
                channel2.send(MessageBuilder.withPayload(person).setHeader("partitionKey",0).build());
                break;
             default:
                 System.out.println("error");
                 break;
        }
    }
}
