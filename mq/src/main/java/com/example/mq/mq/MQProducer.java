//package com.example.mq.mq;
//
//import com.alibaba.fastjson.JSON;
//import com.example.mq.entity.User;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.exception.MQBrokerException;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.remoting.common.RemotingHelper;
//import org.apache.rocketmq.remoting.exception.RemotingException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.UnsupportedEncodingException;
//
///**
// * @program: mq
// * @description: mq producer
// * @author: wenfeng
// * @create: 2019-06-27 18:47
// **/
//@Slf4j
//@Component
//public class MQProducer {
//    @Value("${rocketmq.producer.groupName}")
//    private String groupName;
//    @Value("${rocketmq.producer.namesrvAddr}")
//    private String namesrvAddr;
//    @PostConstruct
//    public void producer(){
//        DefaultMQProducer producer = new DefaultMQProducer(groupName);
//        producer.setNamesrvAddr(namesrvAddr);
//        producer.setVipChannelEnabled(false);
//        try {
//            producer.start();
//            User u = new User("mq");
//            String jsonu = JSON.toJSONString(u);
//            Message msg = new Message("TopicTest","Tag1",jsonu.getBytes(RemotingHelper.DEFAULT_CHARSET));
//            SendResult result = producer.send(msg);
//            log.info(result.getMsgId());
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (RemotingException e) {
//            e.printStackTrace();
//        } catch (MQBrokerException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            producer.shutdown();
//        }
//
//    }
//}
