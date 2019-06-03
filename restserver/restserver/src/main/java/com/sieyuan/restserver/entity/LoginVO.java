package com.sieyuan.restserver.entity;

public class LoginVO {
    private JWT jwt;
    private User user;
    public LoginVO(){}
    public LoginVO(JWT jwt, User user) {
        this.jwt = jwt;
        this.user = user;
    }

    public JWT getJwt() {
        return jwt;
    }

    public User getUser() {
        return user;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
