//package com.example.demo.consumer;
//
//import com.example.common.constant.Constant;
//import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
//import org.apache.rocketmq.client.consumer.PullResult;
//import org.apache.rocketmq.client.exception.MQBrokerException;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.apache.rocketmq.common.message.MessageQueue;
//import org.apache.rocketmq.remoting.exception.RemotingException;
//import org.apache.tomcat.util.bcel.Const;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * pull模式consumer
// *
// * @author wenfeng
// * @date 2020年 05月20日 11:25:58
// */
//@Component
//public class PullConsumerHandler {
//    private static final Map<MessageQueue, Long> offsetTable = new HashMap<>();
//    DefaultMQPullConsumer consumer;
//
////    @PostConstruct
//    public void init() {
//        try {
//            consumer = new DefaultMQPullConsumer(Constant.GROUP);
//            consumer.setNamesrvAddr(Constant.MQADDRESS);
//            consumer.start();
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //    @PostConstruct
//    public void pullConsume() {
////            DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(Constant.GROUP);
////            consumer.setNamesrvAddr(Constant.MQADDRESS);
////            consumer.start();
//        try {
//            Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues(Constant.TOPIC);
//            for (MessageQueue queue : messageQueues) {
//                System.out.println("queue");
//                System.out.println(queue.toString());
//                while (true) {
//                    PullResult result = consumer.pullBlockIfNotFound(queue, null, getMessageQueueOffSet(queue), 32);
//                    setMessageQueueOffSet(queue, result.getNextBeginOffset());
//                    switch (result.getPullStatus()) {
//                        case FOUND:
//                            List<MessageExt> messageExts = result.getMsgFoundList();
//                            System.out.println("body");
//                            for (MessageExt messageExt : messageExts) {
//                                String body = new String(messageExt.getBody());
//                                System.out.println(body);
//                            }
//                            break;
//                        case NO_NEW_MSG:
//                            break;
//                        case NO_MATCHED_MSG:
//                            break;
//                        case OFFSET_ILLEGAL:
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        } catch (RemotingException e) {
//            e.printStackTrace();
//        } catch (MQBrokerException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        consumer.shutdown();
//    }
//
//    private static long getMessageQueueOffSet(MessageQueue messageQueue) {
//        Long offset = offsetTable.get(messageQueue);
//        if (offset != null)
//            return offset;
//        return 0;
//    }
//
//    private static void setMessageQueueOffSet(MessageQueue messageQueueOffSet, long offset) {
//        offsetTable.put(messageQueueOffSet, offset);
//    }
//}
