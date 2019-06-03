package com.sieyuan.restserver.entity;

public class JWT {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expire_in;
    private String scope;
    private String jti;
    public JWT(){}
    public JWT(String access_token, String token_type, String refresh_token, int expire_in, String scope, String jti) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.expire_in = expire_in;
        this.scope = scope;
        this.jti = jti;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public int getExpire_in() {
        return expire_in;
    }

    public String getScope() {
        return scope;
    }

    public String getJti() {
        return jti;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public void setExpire_in(int expire_in) {
        this.expire_in = expire_in;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }
}
