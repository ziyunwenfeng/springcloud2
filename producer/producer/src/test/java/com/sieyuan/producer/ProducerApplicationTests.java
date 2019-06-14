package com.sieyuan.producer;

import com.sieyuan.producer.entity.User;
import com.sieyuan.producer.service.ProductService;
import com.sieyuan.producer.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.HEAD;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerApplicationTests {
    @Autowired
    ProductService productService;
    @Autowired
    @Qualifier(value = "redisUtil")
    RedisUtil redisUtil ;
	@Test
	public void contextLoads() {
	    System.out.println("hello spring cloud");
	}

    @Test
    public void testRedisUtil(){
//	    redisUtil.set("hi","hi");
//	    redisUtil.expire("hi",1000);
//	    redisUtil.set("hello","hello",3000);
//	    System.out.println(redisUtil.get("hi"));
//	    if(redisUtil.hasKey("hello"))
//            System.out.println(redisUtil.get("hello"));
//        System.out.println(redisUtil.hasKey("hi"));
//        List<String> list = new LinkedList();
//        list.add("a");
//        redisUtil.lSet("a",list);
//        Map<String,Object> map = new HashMap<>();
//        map.put("b","b");
//        map.put("bb","bb");
//        redisUtil.hmset("b",map);
//        Set<String> set = new HashSet<>();
//        set.add("c");
//        redisUtil.sSet("c",set);
//        System.out.println(redisUtil.sget("c"));
//        System.out.println(redisUtil.lGet("a",0,1));
//        System.out.println(redisUtil.hmget("b"));

        User u = new User("n");
        redisUtil.set("u",u);
        User user = (User)redisUtil.get("u");
        System.out.println(user.getUsername());


    }

}
