package com.lukas8219.jwt;

import com.lukas8219.jwt.data.jwt.AlgorithmEnum;
import com.lukas8219.jwt.data.jwt.Jwt;

public class JwtToken {

    public static <T> String generateToken(T principal) {
        return new Jwt<>(principal).getToken();
    }

    public static <T> String generateToken(T principal, AlgorithmEnum algorithmEnum){
        return new Jwt<>(principal, algorithmEnum).getToken();
    }

    public static <T> T decodeToken(String token, Class<T> tClass) {
        return JwtDecoder.getPayloadFromToken(token, tClass);
    }

}
