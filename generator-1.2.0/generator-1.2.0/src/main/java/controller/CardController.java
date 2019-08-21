package controller;

import entity.Card;
import service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Author wenfeng
 * Date  2019-07-26
 */
@RestController
@RequestMapping(value = "/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @RequestMapping(value = {"/list", ""}, method = RequestMethod.GET)
    public List<Card> list() {
        List<Card> cards = cardService.findAllList();
        return cards;
    }

    @RequestMapping(value = {"/getById"}, method = RequestMethod.GET)
    public Card getById(@RequestParam("card_no") int card_no) {
        Card card = cardService.get(card_no);
        return card;
    }
    @RequestMapping(value = {"/getList"}, method = RequestMethod.GET)
    public List<Card> getList(@RequestBody Card card) {
        List<Card> cards = cardService.findList(card);
        return cards;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestBody Card card) {
        if (cardService.insert(card) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
    public String insertBatch(@RequestBody List<Card> cards) {
        if (cardService.insertBatch(cards) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody Card card) {
        if (cardService.update(card) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestBody Card card) {
        if (cardService.delete(card) > 0) {
            return "success";
        } else {
            return "failed";
        }
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public String delete(@RequestParam("card_no") int card_no) {
        if (cardService.deleteById(card_no) > 0)
            return "success";
        else
            return "failed";
    }

}
