package com.sieyuan.producer.controller;

import com.sieyuan.producer.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sieyuan.producer.service.PersonService;

import java.util.List;

/**
 * Author wenfeng
 * Date  2019-07-10
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public Object list() {
        List<Person> persons = personService.findAllList();
        return persons;
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public Object get(@RequestParam("id") int id) {
        Person person = personService.get(id);
        return person;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Person person) {
        if (personService.insert(person) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public String insertBatch(@RequestBody List<Person> persons) {
        if (personService.insertBatch(persons) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody Person person) {
        if (personService.update(person) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody Person person) {
        if (personService.delete(person) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        if (personService.deleteById(id) > 0)
            return "success";
        else
            return "failed";
    }

}
