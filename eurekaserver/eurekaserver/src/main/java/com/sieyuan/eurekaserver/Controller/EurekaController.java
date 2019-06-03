package com.sieyuan.eurekaserver.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/eureka")
public class EurekaController {
    private Logger logger = LoggerFactory.getLogger(EurekaController.class);
    @RequestMapping(value="/test",method= RequestMethod.GET)
    public String testController(){
        logger.info("test");
        return "eureka";
    }
}
