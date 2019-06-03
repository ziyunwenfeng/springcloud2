package com.sieyuan.restserver.dao;

import com.sieyuan.restserver.entity.LoginVO;
import com.sieyuan.restserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    public List<User> allUsers();
    public User getByName(String username);
    public LoginVO login(String username,String password);
    public void register(String usernamename,String password);
}
