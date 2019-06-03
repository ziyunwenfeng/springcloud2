package com.sieyuan.eurekaserver_h.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/eureka")
public class EurekaController {
    @RequestMapping(value="/test",method= RequestMethod.GET)
    public String testController(){
        return "eureka";
    }
}
