package com.lukas8219.jwt;

import com.lukas8219.jwt.data.jwt.AlgorithmEnum;
import com.lukas8219.jwt.data.jwt.Jwt;

public class JwtToken {

    public static <T> String generateToken(T principal, String secret) {
        return new Jwt<>(principal, secret).getToken();
    }

    public static <T> String generateToken(T principal, AlgorithmEnum algorithmEnum, String secret){
        return new Jwt<>(principal, algorithmEnum, secret).getToken();
    }

    public static <T> T decodeToken(String token, Class<T> tClass, String secret) {
        return JwtDecoder.getPayloadFromToken(token, tClass, secret);
    }

}
