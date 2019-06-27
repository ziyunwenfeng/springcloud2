package com.example.test.rocketmq;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: test
 * @description: mq consumer config
 * @author: wenfeng
 * @create: 2019-06-27 15:21
 **/
@Configuration
@ConfigurationProperties(prefix = "rocketmq.consumer")
@Setter
@Getter
@ToString
public class ConsumerConfig {
    private String namesrvAddr;
    private String groupName;

}
