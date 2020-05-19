package com.sieyuan.auth.controller;

import com.sieyuan.auth.entity.User;
import com.sieyuan.auth.service.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class AuthController {
    @Autowired
    UserServiceDetail userServiceDetail;
    @GetMapping("/name")
    public User getByName(@RequestParam("name") String name){
        User u = (User) userServiceDetail.loadUserByUsername(name);
        return u;
    }
}
