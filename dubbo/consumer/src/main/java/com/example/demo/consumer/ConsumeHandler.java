package com.example.demo.consumer;

import com.example.common.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 处理消息
 *
 * @author wenfeng
 * @date 2020年 05月19日 18:54:02
 */
@Component
@Slf4j
public class ConsumeHandler {
    @PostConstruct
    public void consume() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(Constant.GROUP);
//        try {
//            consumer.setNamesrvAddr(Constant.MQADDRESS);
//            consumer.subscribe(Constant.TOPIC,"*");
//            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//            consumer.registerMessageListener(new MessageListenerConcurrently() {
//                @Override
//                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                    System.out.println("body");
//                    for (MessageExt messageExt : list) {
//                        String body = new String(messageExt.getBody());
//                        System.out.println(body);
//                        int count = messageExt.getReconsumeTimes();
//                        System.out.println("消费重试次数"+count);
//                        if (count>=2) {
//                            System.out.println("存入数据库");
//                            log.info(messageExt.getKeys());
//                            log.info(messageExt.getTopic());
//                            log.info(body);
//                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//                        }
//                    }
//                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                }
//            });
//            consumer.start();
//            System.out.println("consumer started");
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
        try {
            consumer.setNamesrvAddr(Constant.MQADDRESS);
            consumer.subscribe(Constant.TOPIC,"*");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener((MessageListenerConcurrently)(list,context)-> {
                log.info("body");
                    for (MessageExt messageExt : list) {
                        String body = new String(messageExt.getBody());
                        System.out.println(body);
                        int count = messageExt.getReconsumeTimes();
                        System.out.println("消费重试次数"+count);
                        if (count>=2) {
                            System.out.println("存入数据库");
                            log.info(messageExt.getKeys());
                            log.info(messageExt.getTopic());
                            log.info(body);
                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                        }
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                });
            consumer.start();
            System.out.println("consumer started");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
