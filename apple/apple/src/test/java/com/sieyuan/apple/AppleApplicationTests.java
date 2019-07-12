package com.sieyuan.apple;

import com.sieyuan.apple.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppleApplicationTests {
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
    }

}
