package com.example.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

/**
 * @program: demo
 * @description: process message
 * @author: wenfeng
 * @create: 2019-07-01 09:16
 **/
@Slf4j
@Service
public class MessageHandler implements IMessageHandler {
    @Override
    public boolean handle(MessageExt messageExt) {
        String message = new String(messageExt.getBody());
        log.info("get message : "+message);
        return true;
    }
}
