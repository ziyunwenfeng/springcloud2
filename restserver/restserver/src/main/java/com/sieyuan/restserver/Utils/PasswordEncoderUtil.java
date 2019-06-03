package com.sieyuan.restserver.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static String bCryptPasswordEncoder (String password){
        return encoder.encode(password);
    }
    public static boolean matches(CharSequence rawPassword,String encodedPassword){
        return encoder.matches(rawPassword,encodedPassword);
    }

}
