package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 过滤器测试
 *
 * @author wenfeng
 * @date 2020年 05月25日 14:54:24
 */
@Slf4j
@RestController
//@RequestMapping("/test")
public class FilterTestController {
    @GetMapping("/a")
    public void a() {
        log.info("a");
    }
    @GetMapping("/test/a")
    public void b() {
        log.info("b");
    }
}
