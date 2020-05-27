package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CustomerServiceImpl
 *
 * @author wenfeng
 * @date 2020年 05月26日 14:51:50
 */
@Component
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerMapper mapper;
    @Override
    public Customer getDetailById(String id) {
        return mapper.getDetailById(id);
    }
}
