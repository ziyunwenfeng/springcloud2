package com.sieyuan.security.dao;


import com.sieyuan.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {

    public User getByName(String name);

}
