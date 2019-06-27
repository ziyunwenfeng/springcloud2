package com.example.test.kafka;

import com.example.test.utils.BeanUtils;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @program: test
 * @description: byte to object
 * @author: wenfeng
 * @create: 2019-06-26 19:28
 **/
public class ObjectDeSerializer implements Deserializer {
    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        return BeanUtils.BytesToObject(bytes);
    }

    @Override
    public void close() {

    }
}
