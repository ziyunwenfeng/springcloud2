package com.sieyuan.security.controller;

import com.sieyuan.security.entity.User;
import com.sieyuan.security.service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @Autowired
    UserServiceDetail userServiceDetail;
    @GetMapping("/name")
    public User getByName(@RequestParam("name") String name){
        return (User) userServiceDetail.loadUserByUsername(name);
    }
}
