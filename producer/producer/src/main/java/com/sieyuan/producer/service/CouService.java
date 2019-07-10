package com.sieyuan.producer.service;

import com.sieyuan.producer.dao.CouDao;
import com.sieyuan.producer.entity.Cou;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Author wenfeng
* Date  2019-07-09
*/
@Service
public class CouService {
    @Autowired
    private CouDao couDao;

    public Cou get(int id){
        return couDao.get(id);
    }

    public List<Cou> findList(Cou cou) {
        return couDao.findList(cou);
    }

    public List<Cou> findAllList() {
        return couDao.findAllList();
    }

    public int insert(Cou cou) {
        return couDao.insert(cou);
    }

    public int insertBatch(List<Cou> cous){
        return couDao.insertBatch(cous);
    }

    public int update(Cou cou) {
        return couDao.update(cou);
    }

    public int delete(Cou cou) {
        return couDao.delete(cou);
    }

    public int deleteById(int id){
        return couDao.deleteById(id);
    }

}
