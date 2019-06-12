package com.sieyuan.producer.service;

import com.sieyuan.producer.dao.ProductMapper;
import com.sieyuan.producer.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper ;

    public void descProduct(int id,int num){
        productMapper.descProduct(id,num);
    }

    public Product getAllProduct(){
        return productMapper.getAllProduct();
    }

}
