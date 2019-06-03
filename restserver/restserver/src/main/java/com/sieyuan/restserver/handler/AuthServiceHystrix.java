package com.sieyuan.restserver.handler;

import com.sieyuan.restserver.entity.JWT;
import com.sieyuan.restserver.handler.AuthServiceClient;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceHystrix implements AuthServiceClient {
    @Override
    public JWT getToken(String authorization, String type, String name, String password) {
        System.out.println("hystrix***************");
        return null;
    }
}
