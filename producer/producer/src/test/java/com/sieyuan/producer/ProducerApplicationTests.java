package com.sieyuan.producer;

import com.sieyuan.producer.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.HEAD;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerApplicationTests {
    @Autowired
    ProductService productService;
	@Test
	public void contextLoads() {
	    System.out.println("hello spring cloud");
	}

	@Test
    public  void testDesc(){
	    for(int i=0;i<1003;i++){
            productService.descProduct(2,1);
        }
    }

}
