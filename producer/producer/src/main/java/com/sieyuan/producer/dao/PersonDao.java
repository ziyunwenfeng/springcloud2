package com.sieyuan.producer.dao;

import com.sieyuan.producer.entity.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Author wenfeng
* Date  2019-07-10
*/
@Mapper
public interface PersonDao {

    public Person get(int id);

    public List<Person> findList(Person person);

    public List<Person> findAllList();

    public int insert(Person person);

    public int insertBatch(List<Person> persons);

    public int update(Person person);

    public int delete(Person person);

    public int deleteById(int id);

}