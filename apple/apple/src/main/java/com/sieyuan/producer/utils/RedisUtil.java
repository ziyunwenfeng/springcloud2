package com.sieyuan.producer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: producer
 * @description: RedisUtil
 * @author: wenfeng
 * @create: 2019-06-12 23:23
 **/
@Component(value = "redisUtil")
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //-------------------------common------------------
    /*
     * description:判断是否过期
     * @param key ：
     * @param time
     * @Return: boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 0:06
     */
    public boolean expire(String key,long time){
        try {
            if(time > 0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * description:根据key获取过期时间
     * @param key
     * @Return: long
     * @Author: wenfeng
     * @Date: 2019/6/13 0:08
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /*
     * description:判断key是否存在
     * @param key
     * @Return: boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 0:10
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * description:删除缓存
     * @param key 可以传入一个值，也可以多个值
     * @Return: void
     * @Author: wenfeng
     * @Date: 2019/6/13 0:14
     */
    public void deleteCache(String... key){
        if(key != null && key.length > 0){
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
    //-------------------------string--------------------
    /*
     * description:根据key获取缓存中value
     * @param key
     * @Return: java.lang.Object
     * @Author: wenfeng
     * @Date: 2019/6/13 0:17
     */
    public Object get(String key){
//        return key==null?null:redisTemplate.opsForValue().get(key);
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        Object o = ops.get(key);
        return o;
    }

    /*
     * description:存入缓存
     * @param key
     * @param value
     * @Return: boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 0:20
     */
    public boolean set(String key, Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * description:设置缓存并设置过期时间
     * @param key
     * @param value
     * @param time
     * @Return: boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 0:23
     */
    public boolean set(String key, Object value,long time){
        try {
            if (time>0) {
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            } else {
                set(key,value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * description:递减
     * @param key
     * @param delta
     * @Return: long
     * @Author: wenfeng
     * @Date: 2019/6/13 0:27
     */
    public long incr(String key,long delta){
        if(delta<0){
            throw new RuntimeException("delta must > 0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }
    /*
     * description:递增
     * @param key
     * @param delta
     * @Return: long
     * @Author: wenfeng
     * @Date: 2019/6/13 0:27
     */
    public long desc(String key,long delta){
        if(delta>0){
            throw new RuntimeException("delta must > 0");
        }
        return redisTemplate.opsForValue().increment(key,-delta);
    }
    //--------------------map----------------------
    /*
     * description:HashGet
     * @param key
     * @param item
     * @Return: java.lang.Object
     * @Author: wenfeng
     * @Date: 2019/6/13 0:29
     */
    public Object hget(String key,String item){
        return redisTemplate.opsForHash().get(key,item);
    }

    /*
     * description:根据key获取键值
     * @param key
     * @Return: java.util.Map<java.lang.Object,java.lang.Object>
     * @Author: wenfeng
     * @Date: 2019/6/13 0:35
     */
    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }
    /*
     * description:HashSet
     * @param key
     * @param map
     * @Return: boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 0:33
     */
    public boolean hmset(String key,Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * description:HashSet 并设置时间
     * @param key
     * @param map
     * @param time
     * @Return: boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 0:55
     */
    public boolean hmset(String key,Map<String,Object> map,long time){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            if(time > 0){
                expire(key,time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * description:hash表中存入数据，如果不存在就新创建
     * @param key
     * @param item
     * @param value
     * @Return: boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 0:58
     */
    public boolean hset(String key,String item,Object value){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    /*
     * description:hashset存入值，并设置时间
     * @param key
     * @param item
     * @param value
     * @param time
     * @Return: boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 1:00
     */
    public boolean hset(String key,String item,Object value,long time){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            if(time > 0){
                expire(key,time);
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    /*
     * description:删除hash表中的值
     * @param key
     * @param item
     * @Return: void
     * @Author: wenfeng
     * @Date: 2019/6/13 1:02
     */
    public void hdel(String key,Object... item){
        redisTemplate.opsForHash().delete(key,item);
    }

    /*
     * description:判断是否有该项
     * @param key
     * @param item
     * @Return: java.lang.Boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 1:04
     */
    public Boolean hHasKey(String key,String item){
        return redisTemplate.opsForHash().hasKey(key,item);
    }

    /*
     * description:递增
     * @param key
     * @param item
     * @param by
     * @Return: double
     * @Author: wenfeng
     * @Date: 2019/6/13 1:06
     */
    public double hincr(String key,String item,double by){
        return redisTemplate.opsForHash().increment(key,item,by);
    }

    /*
     * description:递减
     * @param key
     * @param item
     * @param by
     * @Return: double
     * @Author: wenfeng
     * @Date: 2019/6/13 1:07
     */
    public double hdecr(String key,String item,double by){
        return redisTemplate.opsForHash().increment(key,item,-by);
    }

    //--------------------------set-----------------
    /*
     * description:根据key获取所有值
     * @param key
     * @Return: java.util.Set<java.lang.Object>
     * @Author: wenfeng
     * @Date: 2019/6/13 15:40
     */
    public Set<Object> sget(String key){
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * description:判断key,value是否存在
     * @param key
     * @param value
     * @Return: boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 15:43
     */
    public boolean sHasKey(String key,Object value){
        try {
            return redisTemplate.opsForSet().isMember(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * description:数据存入set缓存
     * @param key
     * @param values
     * @Return: long
     * @Author: wenfeng
     * @Date: 2019/6/13 15:46
     */
    public long sSet(String key,Object... values){
        try {
            return redisTemplate.opsForSet().add(key,values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * description:数据存入缓存并设置时间
     * @param key
     * @param time
     * @param values
     * @Return: long
     * @Author: wenfeng
     * @Date: 2019/6/13 15:50
     */
    public long sSet(String key,long time,Object... values){
        try {
            long count =  redisTemplate.opsForSet().add(key,values);
            if (time > 0) {
                expire(key,time);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * description:获取set缓存的长度
     * @param key
     * @Return: long
     * @Author: wenfeng
     * @Date: 2019/6/13 15:52
     */
    public long sSetSize(String key){
        try {
            return  redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * description:删除value
     * @param key
     * @param values
     * @Return: long
     * @Author: wenfeng
     * @Date: 2019/6/13 15:54
     */
    public long sRemove(String key,Object... values){
        try {
            return redisTemplate.opsForSet().remove(key,values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //--------------------list----------------
    /*
     * description:获取缓存中值
     * @param key
     * @param start
     * @param end
     * @Return: java.util.List<java.lang.Object>
     * @Author: wenfeng
     * @Date: 2019/6/13 15:57
     */
    public List<Object> lGet(String key,long start,long end){
        try {
            return redisTemplate.opsForList().range(key,start,end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * description:list长度
     * @param key
     * @Return: long
     * @Author: wenfeng
     * @Date: 2019/6/13 16:00
     */
    public long lGetListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * description:获取list中的值
     * @param key
     * @param index
     * @Return: java.lang.Object
     * @Author: wenfeng
     * @Date: 2019/6/13 16:02
     */
    public Object lGetIndex(String key,long index){
        try {
            return redisTemplate.opsForList().index(key,index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * description:将list放入缓存
     * @param key
     * @param value
     * @Return: boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 16:14
     */
    public boolean lSet(String key,Object value){
        try {
            redisTemplate.opsForList().rightPush(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /*
     * description:将list放入缓存并设置时间
     * @param key
     * @param value
     * @param time
     * @Return: boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 16:16
     */
    public boolean lSet(String key,Object value,long time){
        try {
            redisTemplate.opsForList().rightPush(key,value);
            if(time>0)
                expire(key,time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /*
     * description:更新index处的数据
     * @param key
     * @param index
     * @param value
     * @Return: boolean
     * @Author: wenfeng
     * @Date: 2019/6/13 16:24
     */
    public boolean lUpdateIndex(String key,long index,Object value){
        try {
            redisTemplate.opsForList().set(key,index,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long lRemove(String key,long count,Object value){
        try {
            return redisTemplate.opsForList().remove(key,count,value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
