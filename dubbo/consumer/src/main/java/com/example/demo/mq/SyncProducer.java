package com.example.demo.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("just_for_test");
        producer.setVipChannelEnabled(false);
        producer.setNamesrvAddr("106.14.188.199:9876");
        producer.start();
        for (int i = 0; i < 3; i++) {
            Message message = new Message("topic_for_test","TagA",("hello "+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult result = producer.send(message,10000);
            System.out.println(result);
        }
//        producer.shutdown();
    }
}
