package com.example.mq.rocketmq;

import org.apache.rocketmq.common.message.MessageExt;

public interface IMessageHandler {
    public boolean handle(MessageExt messageExt);
}
