package com.example.mq.rocketmq;

import org.apache.rocketmq.common.message.MessageExt;

public interface IMessageHandler<T> {
    public boolean handle(MessageExt messageExt);
}
