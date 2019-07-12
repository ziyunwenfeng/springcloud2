package com.sieyuan.producer.controller;

import com.sieyuan.producer.entity.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sieyuan.producer.service.OrgService;

import java.util.List;

/**
 * Author wenfeng
 * Date  2019-07-09
 */
@RestController
@RequestMapping(value = "/org")
public class OrgController {
    @Autowired
    private OrgService orgService;

    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public Object list() {
        List<Org> orgs = orgService.findAllList();
        return orgs;
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public Object get(@RequestParam("id") int id) {
        Org org = orgService.get(id);
        return org;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Org org) {
        if (orgService.insert(org) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public String insertBatch(@RequestBody List<Org> orgs) {
        if (orgService.insertBatch(orgs) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody Org org) {
        if (orgService.update(org) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody Org org) {
        if (orgService.delete(org) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        if (orgService.deleteById(id) > 0)
            return "success";
        else
            return "failed";
    }

}
