package com.sieyuan.producer.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sieyuan.producer.utils.RedisUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableAutoConfiguration
public class RedisConfig {
    @Bean
//    @ConditionalOnMissingBean(name="redisTemplate")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String ,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
//        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        serializer.setObjectMapper(mapper);

        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setValueSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();

        template.setEnableTransactionSupport(true);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        //默认设置
            RedisCacheConfiguration redisCacheConfiguration =
                RedisCacheConfiguration
                        .defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(1))
                        .disableCachingNullValues();
        //设置初始化缓存空间
        Set<String> cacheNames = new HashSet<>();
        cacheNames.add("timeGroup");
        cacheNames.add("user");
        //不同缓存设置不同的配置
        Map<String,RedisCacheConfiguration> configurationMap = new HashMap<>();
        configurationMap.put("timeGroup",redisCacheConfiguration);
        configurationMap.put("user",redisCacheConfiguration.entryTtl(Duration.ofSeconds(120)));

        RedisCacheManager cacheManager =
                RedisCacheManager
                        .builder(redisConnectionFactory)
                        .initialCacheNames(cacheNames)
                        .withInitialCacheConfigurations(configurationMap)
                        .build();
        return cacheManager;
    }


}
