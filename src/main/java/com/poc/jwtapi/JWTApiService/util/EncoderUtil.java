package com.poc.jwtapi.JWTApiService.util;


import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 *
 * Class for
 * <br>
 * <br>
 *
 * @author AshishD
 * @since date
 * -------------------------------------------------------------------
 */

public class EncoderUtil {

    public static PasswordEncoder getEncoder(){
        DelegatingPasswordEncoder encoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        encoder.setDefaultPasswordEncoderForMatches(new MessageDigestPasswordEncoder("MD5"));
        return encoder;
    }

    public static String encode(String s){
        return getEncoder().encode(s);
    }

    public static String encodewithMd5(String s){
        return new MessageDigestPasswordEncoder("MD5").encode(s);
    }
}