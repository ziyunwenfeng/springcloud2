package com.sieyuan.security.entity;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    private String id;
    private String name;
    public Role(){}
    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
