package com.sieyuan.auth.dao;


import com.sieyuan.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public User getByName(String name);

}
