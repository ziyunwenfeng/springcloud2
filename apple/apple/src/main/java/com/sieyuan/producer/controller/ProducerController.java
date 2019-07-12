package com.sieyuan.producer.controller;

import com.sieyuan.producer.entity.User;
import com.sieyuan.producer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProducerController {
    Logger logger = LoggerFactory.getLogger(ProducerController.class);
    @Autowired
    UserService userService;
    @RequestMapping(value="/consumer",method = RequestMethod.GET)
    public String produce(){
        System.out.println("producer");
        return "producer";
    }
    @RequestMapping(value="/getByUsername",method = RequestMethod.GET)
    public User getByUsername(@RequestParam("username") String username){
        logger.info("username: "+username);
        return userService.getByUsername(username);
    }

    @RequestMapping(value="/insertUser",method = RequestMethod.POST)
    public User insertUser(@RequestBody User user){
        logger.info("username: "+user);
        return userService.insertUser(user);
    }
    @RequestMapping(value="/updateUser",method = RequestMethod.PUT)
    public User updateUser(@RequestParam("username") String username,@RequestParam("password") String password){
        logger.info("username: "+username);
        return userService.updateUser(username,password);
    }

    @RequestMapping(value="/deleteUser",method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam("username") String username){
        logger.info("username: "+username);
        userService.deleteUser(username);
    }
}
