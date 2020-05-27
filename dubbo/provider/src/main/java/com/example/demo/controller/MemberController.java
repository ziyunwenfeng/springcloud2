package com.example.demo.controller;

import com.example.common.annotation.Deleted;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员测试
 *
 * @author wenfeng
 * @date 2020年 05月26日 14:39:51
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping(value = {"/getById/{id}"}, method = RequestMethod.GET)
    @Deleted(deleted = "N")
    public Customer getById(@PathVariable("id") String id) {
        return customerService.getDetailById(id);
    }
}
