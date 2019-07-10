package controller;

import entity.Cou;
import service.CouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Author wenfeng
 * Date  2019-07-09
 */
@RestController
@RequestMapping(value = "/cou")
public class CouController {
    @Autowired
    private CouService couService;

    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public Object list() {
        List<Cou> cous = couService.findAllList();
        return cous;
    }

    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public Object get(@RequestParam("id") int id) {
        Cou cou = couService.get(id);
        return cou;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Cou cou) {
        if (couService.insert(cou) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public String insertBatch(@RequestBody List<Cou> cous) {
        if (couService.insertBatch(cous) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody Cou cou) {
        if (couService.update(cou) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody Cou cou) {
        if (couService.delete(cou) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        if (couService.deleteById(id) > 0)
            return "success";
        else
            return "failed";
    }

}
