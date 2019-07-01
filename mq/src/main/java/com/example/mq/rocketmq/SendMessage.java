package com.example.mq.rocketmq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description: mq send message
 * @author: wenfeng
 * @create: 2019-07-01 16:52
 **/
@Slf4j
@Component
public class SendMessage {
    @Autowired
    private DefaultMQProducer defaultMQProducer;
    public void send(String topic,String tags,Object object){
        String strOfObject = JSON.toJSONString(object);
        Message message = new Message(topic,tags,strOfObject.getBytes());
        try {
            defaultMQProducer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("success");
                    log.info(JSON.toJSONString(sendResult));
                }
                @Override
                public void onException(Throwable throwable) {
                    log.info("fail");
                }
            });
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
