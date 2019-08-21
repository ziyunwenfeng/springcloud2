package dao;

import entity.Card;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

/**
* Author wenfeng
* Date  2019-07-26
*/
@Mapper
public interface CardDao {

    public Card get(int card_no);

    public List<Card> findList(Card card);

    public List<Card> findAllList();

    public int insert(Card card);

    public int insertBatch(List<Card> cards);

    public int update(Card card);

    public int delete(Card card);

    public int deleteById(int card_no);

}