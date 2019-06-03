package com.sieyuan.restserver.controller;

import com.sieyuan.restserver.entity.LoginVO;
import com.sieyuan.restserver.entity.User;
import com.sieyuan.restserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class RestServerController {
    @Autowired
    UserService userService;
    @GetMapping("/all")
    public List<User> allUsers(){
        return userService.allUsers();
    }
    @PostMapping("/login")
    public LoginVO login(@RequestParam("username") String username,@RequestParam("password") String password){
        return userService.login(username,password);
    }
    @PostMapping("/register")
    public void register(@RequestParam("username") String username,@RequestParam("password") String password){
        userService.register(username,password);
    }
}
