package com.example.test.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @program: test
 * @description: test consumer
 * @author: wenfeng
 * @create: 2019-06-27 16:03
 **/
@Configuration
@Slf4j
public class TestComsumer extends Consumer implements ApplicationListener {
    @Override
    public ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs) {
        int num = 1;
        for(MessageExt msg : msgs){
            try {
                String str = new String(msg.getBody(),"utf-8");
                log.info(str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        try {
            super.listener("TopicTest","Tags1");
        } catch (MQClientException e) {
            e.printStackTrace();
            log.info("消费者监听失败");
        }
    }
}
