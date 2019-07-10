package com.sieyuan.producer.controller;

import com.sieyuan.producer.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sieyuan.producer.service.TestService;

import java.util.List;

/**
 * Author wenfeng
 * Date  2019-07-09
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public Object list() {
        List<Test> tests = testService.findAllList();
        return tests;
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public Object get(@RequestParam("id") int id) {
        Test test = testService.get(id);
        return test;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Test test) {
        if (testService.insert(test) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public String insertBatch(@RequestBody List<Test> tests) {
        if (testService.insertBatch(tests) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody Test test) {
        if (testService.update(test) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody Test test) {
        if (testService.delete(test) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/delete2", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        if (testService.delete2(id) > 0)
            return "success";
        else
            return "failed";
    }

}
