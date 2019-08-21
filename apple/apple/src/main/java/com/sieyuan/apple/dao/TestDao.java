package com.sieyuan.apple.dao;

import com.sieyuan.apple.entity.Page;
import com.sieyuan.apple.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

/**
* Author wenfeng
* Date  2019-07-11
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

    public int deleteById(int id);

    public List<Test> getPage(Page page);

}