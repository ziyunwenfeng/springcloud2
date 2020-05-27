package com.example.demo.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * mybatis 配置类
 *
 * @author wenfeng
 * @date 2020年 05月26日 16:51:22
 */
@Configuration
public class MybatisConfiguration {

    @Bean
    public MybatisInterceptor interceptorTest() {
        MybatisInterceptor interceptorTest = new MybatisInterceptor();
        Properties properties = new Properties();
        interceptorTest.setProperties(properties);
        return interceptorTest;
    }
}
