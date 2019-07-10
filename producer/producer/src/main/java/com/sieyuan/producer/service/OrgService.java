package com.sieyuan.producer.service;

import com.sieyuan.producer.dao.OrgDao;
import com.sieyuan.producer.entity.Org;
import com.sieyuan.producer.entity.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Author wenfeng
* Date  2019-07-09
*/
@Service
public class OrgService {
    @Autowired
    private OrgDao orgDao;

    public Org get(int id){
        return orgDao.get(id);
    }

    public List<Org> findList(Org org) {
        return orgDao.findList(org);
    }

    public List<Org> findAllList() {
        return orgDao.findAllList();
    }

    public int insert(Org org) {
        return orgDao.insert(org);
    }

    public int insertBatch(List<Org> orgs){
        return orgDao.insertBatch(orgs);
    }

    public int update(Org org) {
        return orgDao.update(org);
    }

    public int delete(Org org) {
        return orgDao.delete(org);
    }

    public int deleteById(int id){
        return orgDao.deleteById(id);
    }

}
