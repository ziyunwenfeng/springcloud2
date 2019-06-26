package com.sieyuan.restserver.service;

import com.sieyuan.restserver.Utils.PasswordEncoderUtil;
import com.sieyuan.restserver.handler.AuthServiceClient;
import com.sieyuan.restserver.handler.LoginException;
import com.sieyuan.restserver.dao.UserMapper;
import com.sieyuan.restserver.entity.JWT;
import com.sieyuan.restserver.entity.LoginVO;
import com.sieyuan.restserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserMapper userInterface;
    @Autowired
    AuthServiceClient client;
    public List<User> allUsers(){
        return userInterface.allUsers();
    }
    public User getByName(String username){
        return (User) userInterface.getByName(username);
    }
    public LoginVO login(String username,String password){
        User user = (User)getByName(username);
//        if(null==user){
//            throw new LoginException("name error!");
//        }
//        if(!PasswordEncoderUtil.matches(password,user.getPassword())){
//            throw new LoginException("password error");
//        }
        JWT jwt = client.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password",username,password);
        if(jwt==null){
            throw new LoginException("internal error");
        }else
            System.out.println("not null!!!!!!!!!!!!!!!!");
        LoginVO loginVO = new LoginVO(jwt,user);
        System.out.println("*********************");
        System.out.println(loginVO.getUser().getUsername());
        System.out.println("*********************");
        return loginVO;
    }
    public void register(String username,String password){
        userInterface.register(username, PasswordEncoderUtil.bCryptPasswordEncoder(password));
    }
}
