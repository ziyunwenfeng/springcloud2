package com.sieyuan.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.HEAD;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerApplicationTests {

	@Test
	public void contextLoads() {
	    System.out.println("hello spring cloud");
	}

}
