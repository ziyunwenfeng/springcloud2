package service;

import dao.TestDao;
import entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Author wenfeng
* Date  2019-07-10
*/
@Service
public class TestService {
    @Autowired
    private TestDao testDao;

    public Test get(int id){
        return testDao.get(id);
    }

    public List<Test> findList(Test test) {
        return testDao.findList(test);
    }

    public List<Test> findAllList() {
        return testDao.findAllList();
    }

    public int insert(Test test) {
        return testDao.insert(test);
    }

    public int insertBatch(List<Test> tests){
        return testDao.insertBatch(tests);
    }

    public int update(Test test) {
        return testDao.update(test);
    }

    public int delete(Test test) {
        return testDao.delete(test);
    }

    public int deleteById(int id){
        return testDao.deleteById(id);
    }

}
