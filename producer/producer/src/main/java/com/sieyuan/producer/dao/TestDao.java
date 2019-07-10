package com.sieyuan.producer.dao;

import com.sieyuan.producer.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Author wenfeng
* Date  2019-07-09
*/
@Mapper
public interface TestDao {

    public Test get(int id);

    public List<Test> findList(Test test);

    public List<Test> findAllList();

    public int insert(Test test);

    public int insertBatch(List<Test> tests);

    public int update(Test test);

    public int delete(Test test);

    public int delete2(int id);

}