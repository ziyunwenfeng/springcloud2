package com.example.test.kafka;

import com.example.test.utils.BeanUtils;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @program: test
 * @description: object serializer
 * @author: wenfeng
 * @create: 2019-06-26 17:47
 **/
public class ObjectSerializer implements Serializer {

    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Object o) {
        return BeanUtils.ObjectToByte(o);
    }

    @Override
    public void close() {

    }
}
