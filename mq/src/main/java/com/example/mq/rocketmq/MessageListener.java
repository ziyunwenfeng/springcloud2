package com.example.mq.rocketmq;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: demo
 * @description: listen message
 * @author: wenfeng
 * @create: 2019-07-01 09:20
 **/
public class MessageListener implements MessageListenerConcurrently {
    @Autowired
    IMessageHandler messageHandler;
    public void setMessageHandler(IMessageHandler messageHandler){
        this.messageHandler = messageHandler;
    }
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        MessageExt messageExt = list.get(0);
        boolean result = messageHandler.handle(messageExt);
        if(!result){
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
    }
}
