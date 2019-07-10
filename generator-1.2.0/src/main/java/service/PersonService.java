package service;

import dao.PersonDao;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Author wenfeng
* Date  2019-07-10
*/
@Service
public class PersonService {
    @Autowired
    private PersonDao personDao;

    public Person get(int id){
        return personDao.get(id);
    }

    public List<Person> findList(Person person) {
        return personDao.findList(person);
    }

    public List<Person> findAllList() {
        return personDao.findAllList();
    }

    public int insert(Person person) {
        return personDao.insert(person);
    }

    public int insertBatch(List<Person> persons){
        return personDao.insertBatch(persons);
    }

    public int update(Person person) {
        return personDao.update(person);
    }

    public int delete(Person person) {
        return personDao.delete(person);
    }

    public int deleteById(int id){
        return personDao.deleteById(id);
    }

}
