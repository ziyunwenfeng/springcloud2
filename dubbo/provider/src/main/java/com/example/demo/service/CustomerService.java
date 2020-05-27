package com.example.demo.service;

import com.example.demo.entity.Customer;

/**
 * customerService
 *
 * @author wenfeng
 * @date 2020年 05月26日 14:50:27
 */
public interface CustomerService {
    Customer getDetailById(String id);
}
