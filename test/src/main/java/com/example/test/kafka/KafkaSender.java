package com.example.test.kafka;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @program: test
 * @description: kafka send Object
 * @author: wenfeng
 * @create: 2019-06-26 16:06
 **/
@Component
@Slf4j
public class KafkaSender<T> {

    @Autowired
//    KafkaTemplate<String,Object> kafkaTemplate;
    KafkaTemplate kafkaTemplate;
    /*
     * description: send message
     * @param T obj
     * @Return: void
     * @Author: wenfeng
     * @Date: 2019/6/26 16:08
     */
    public void send(T obj,String topic){
//        String message = JSON.toJSONString(obj);
//        log.info("message : "+ message);
//        ListenableFuture<SendResult<String,Object>> future = kafkaTemplate.send(0,message);
        ListenableFuture<SendResult<String,Object>> future = kafkaTemplate.send(topic,obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("message send error "+ throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                log.info("message send success");
                log.info("result : "+ stringObjectSendResult.toString());
            }
        });
    }
}
