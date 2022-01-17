package com.lukas8219.jwt.data.jwt;

public class Jwt<T> {

    private final JwtBody<T> body;

    public Jwt(T principal, AlgorithmEnum algorithmEnum, String secret){
        body = createJwtBody(principal, algorithmEnum,secret);
    }

    public Jwt(T principal, String secret){
        body = createJwtBody(principal, null,secret);
    }

    private JwtBody<T> createJwtBody(T t, AlgorithmEnum algorithmEnum, String secret) {
        if(algorithmEnum == null){
            algorithmEnum = AlgorithmEnum.HS256;
        }
        var header = new JwtHeader(algorithmEnum);
        var payload = new JwtPayload<>(t);
        var signature = new JwtSignature(header, payload,secret);
        return new JwtBody<>(header, payload, signature);
    }

    public String getToken(){
        return body.getJwt();
    }

}
