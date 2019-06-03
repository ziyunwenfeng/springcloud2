package com.sieyuan.restserver.handler;

public class LoginException extends RuntimeException {
    public LoginException(String message){
        super(message);
    }
}
