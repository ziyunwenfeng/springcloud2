package com.sieyuan.producer.service;

import com.sieyuan.producer.dao.UserMapper;
import com.sieyuan.producer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper mapper ;//"#root.targetClass+#username"
    @Cacheable(value = "user",key = "#root.targetClass+#username",unless = "#user eq null ")
    public User getByUsername(String username){
        return mapper.getByUsername(username);
    }
    @CachePut(value = "user",key = "#root.targetClass+#user.username",unless = "#user eq null ")
    public User insertUser(User user){
        mapper.insertUser(user);
        return user;
    }
    @CachePut(value = "user",key = "#root.targetClass+#username",unless = "#username eq null ")
    public User updateUser(String username,String password){
        mapper.updateUser(username,password);
        return mapper.getByUsername(username);
    }
    @CacheEvict(value = "user",key ="#root.targetClass+#username",condition = "#result eq true")
    public boolean deleteUser(String username){
        mapper.deleteUser(username);
        return true;
    }
}
