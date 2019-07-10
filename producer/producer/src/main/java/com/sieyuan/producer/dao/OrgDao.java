package com.sieyuan.producer.dao;

import com.sieyuan.producer.entity.Org;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Author wenfeng
* Date  2019-07-09
*/
@Mapper
public interface OrgDao {

    public Org get(int id);

    public List<Org> findList(Org org);

    public List<Org> findAllList();

    public int insert(Org org);

    public int insertBatch(List<Org> orgs);

    public int update(Org org);

    public int delete(Org org);

    public int deleteById(int id);

}