package com.sieyuan.apple.controller;

//import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.sieyuan.apple.entity.Page;
import com.sieyuan.apple.entity.Test;
import com.sieyuan.apple.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @program: producer
 * @description: test
 * @author: wenfeng
 * @create: 2019-07-12 10:17
 **/
@Slf4j
@RestController
public class TestController {
    @Autowired
    TestService testService;
//    @GetMapping("/test")
//    public PageInfo<Test> test(){
//        Page<Test> page = PageHelper.startPage(2,2);
//        log.info("pageNum "+page.getPageNum());
//        log.info("pageSize "+page.getPageSize());
//        log.info("total "+page.getTotal());
//        log.info("pages "+page.getPages());
//
//        PageInfo pageInfo = new PageInfo(testService.findAllList());
//
//        log.info("pageInfo pageNum "+pageInfo.getPageNum());
//        log.info("pageInfo pageSize "+pageInfo.getPageSize());
//        log.info("pageInfo total "+pageInfo.getTotal());
//        log.info("pageInfo pages "+pageInfo.getPages());
//
//        return pageInfo;
//    }
//
//    @GetMapping("/get")
//    public Page<Test> all(){
//        Page<Test> page = new Page<Test>();
//        page.setPageNo(2);
//        page.setPageSize(2);
//        List<Test> tests = testService.getPage(page);
//        page.setResults(tests);
//        return page;
//    }
    public void test(){


    }

}
