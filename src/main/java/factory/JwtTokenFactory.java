package factory;

import decoder.JwtDecoder;
import jwt.AlgorithmEnum;
import jwt.Jwt;

public class JwtTokenFactory {

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
