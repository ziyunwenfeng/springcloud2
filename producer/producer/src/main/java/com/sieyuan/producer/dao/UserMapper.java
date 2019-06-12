package com.sieyuan.producer.dao;

import com.sieyuan.producer.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getByUsername(String username);
    void insertUser(User user);
    void updateUser(String username,String password);
    void deleteUser(String name);
}
