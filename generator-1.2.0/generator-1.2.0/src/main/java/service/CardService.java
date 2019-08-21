package service;

import dao.CardDao;
import entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* Author wenfeng
* Date  2019-07-26
*/
@Service
public class CardService {
    @Autowired
    private CardDao cardDao;

    public Card get(int card_no){
        return cardDao.get(card_no);
    }

    public List<Card> findList(Card card) {
        return cardDao.findList(card);
    }

    public List<Card> findAllList() {
        return cardDao.findAllList();
    }

    public int insert(Card card) {
        return cardDao.insert(card);
    }

    public int insertBatch(List<Card> cards){
        return cardDao.insertBatch(cards);
    }

    public int update(Card card) {
        return cardDao.update(card);
    }

    public int delete(Card card) {
        return cardDao.delete(card);
    }

    public int deleteById(int card_no){
        return cardDao.deleteById(card_no);
    }

}
