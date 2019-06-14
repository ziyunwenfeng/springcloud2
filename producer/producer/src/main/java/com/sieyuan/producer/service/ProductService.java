package com.sieyuan.producer.service;

import com.sieyuan.producer.dao.ProductMapper;
import com.sieyuan.producer.entity.Product;
import com.sieyuan.producer.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper ;

    @Autowired
    RedisUtil redisUtil;
    public void descProduct(int id,int num){
        productMapper.descProduct(id,num);
    }

    public List<Product> getAllProduct(){
        String key = "allProduct";
        if (redisUtil.hasKey(key)) {
            System.out.println("from cache");
            return (List)redisUtil.lGet(key,0,-1);
        } else {
            System.out.println("from db");
            List<Product> products = productMapper.getAllProduct();
            redisUtil.lSet(key,products,30);
            return products;
        }

    }

}
