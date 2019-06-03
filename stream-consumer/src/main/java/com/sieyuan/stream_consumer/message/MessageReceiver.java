package com.sieyuan.stream_consumer.message;

import com.sieyuan.stream_consumer.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@EnableBinding(value = {InputInterface.class})
public class MessageReceiver {
    private Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
//    @StreamListener(InputInterface.INPUT1)
    public void receiveFromChannel(Message<String> payload, @Header(AmqpHeaders.CONSUMER_QUEUE) String partition){
//       logger.info(payload.getPayload());
        logger.info("hello  "+ payload);
    }

    @StreamListener(InputInterface.INPUT2)
    public void receiveFromChannel2(Person person, @Header(AmqpHeaders.CONSUMER_QUEUE) String partition){
        logger.info(person.getName());
        logger.info(partition);
    }

    @StreamListener(InputInterface.INPUT1)
    public void receiveFromChannel(Object object){
        logger.info("hello "+object);
    }
}
