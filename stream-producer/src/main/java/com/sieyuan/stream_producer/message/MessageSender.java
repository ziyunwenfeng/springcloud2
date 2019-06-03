package com.sieyuan.stream_producer.message;

import com.sieyuan.stream_producer.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableBinding(value = {OutputInterface.class})
@EnableScheduling
public class MessageSender {
    private Logger logger = LoggerFactory.getLogger(MessageSender.class);
    @Autowired
    OutputInterface outputInterface;

    @Scheduled(initialDelay = 1000,fixedRate = 2000)
    public void sendMessage(){
        Message message =
                MessageBuilder
                        .withPayload("outputInterface")
                        .setHeader("partitionKey",1)
                        .build();
        logger.info(message.getPayload().toString());
//        outputInterface.output1().send(message);
    }


    @Qualifier(OutputInterface.OUTPUT2)
    @Autowired
    MessageChannel output ;
    @Scheduled(initialDelay =  1000, fixedRate = 4000)
    public void sendMessage2(){
        Person person = new Person("sieyuan");
//        output
//                .send(MessageBuilder.withPayload(person)
//                .setHeader("partitionKey",2)
//                .build());
    }
}
