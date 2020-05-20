package com.example.demo.controller;

import com.example.common.constant.Constant;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * 生产者产生消息
 *
 * @author wenfeng
 * @date 2020年 05月19日 18:47:05
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {
    @GetMapping("/send")
    public void produce() {
        DefaultMQProducer producer = new DefaultMQProducer(Constant.GROUP);
        try {
            producer.setNamesrvAddr(Constant.MQADDRESS);
            producer.setVipChannelEnabled(false);
            producer.setRetryTimesWhenSendFailed(10);
            producer.start();
            for (int i = 0; i < 3; i++) {
                Message message = new Message(Constant.TOPIC, "TagA", ("hello " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult result = producer.send(message, 10000);
                System.out.println(result);
            }
        } catch (MQClientException | UnsupportedEncodingException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
