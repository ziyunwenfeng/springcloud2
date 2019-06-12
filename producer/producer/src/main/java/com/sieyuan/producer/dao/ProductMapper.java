package com.sieyuan.producer.dao;

import com.sieyuan.producer.entity.Product;
import com.sieyuan.producer.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    void descProduct(int id,int num);
    Product getAllProduct();
}
