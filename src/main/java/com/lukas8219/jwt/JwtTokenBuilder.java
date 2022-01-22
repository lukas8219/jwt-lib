package com.lukas8219.jwt;

import com.lukas8219.jwt.data.jwt.AlgorithmEnum;
import com.lukas8219.jwt.data.jwt.Jwt;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenBuilder {

    private String secret;
    private Object payload;
    private Map<String, String> claims;
    private AlgorithmEnum algorithmEnum = AlgorithmEnum.HS256;

    private JwtTokenBuilder(){}

    public static  JwtTokenBuilder newBuilder() {
        return new JwtTokenBuilder();
    }

    public JwtTokenBuilder addClaim(String claim) {
        if (claims == null) {
            claims = new HashMap<>();
        }
        claims.put(claim, claim);
        return this;
    }

    public JwtTokenBuilder withClaim(Map<String, String> claim) {
        claims = claim;
        return this;
    }

    public JwtTokenBuilder withPayload(Object payload) {
        this.payload = payload;
        return this;
    }

    public JwtTokenBuilder withSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public JwtTokenBuilder withAlgorithm(AlgorithmEnum algorithm) {
        this.algorithmEnum = algorithm;
        return this;
    }

    public <T> JwtDecoder<T> buildDecoder(Class<T> clazz) {
        if (payload == null) {
            throw new RuntimeException("Payload cannot be Null");
        }

        if (secret == null) {
            throw new RuntimeException("Secret cannot be null");
        }

        return new JwtDecoder<T>(clazz, secret);
    }

    public String build() {
        if (secret == null) {
            throw new RuntimeException("Secret missing");
        }

        if (claims == null || claims.isEmpty()) {
            throw new RuntimeException("Claims missing");
        }

        if (payload == null) {
            throw new RuntimeException("Payload missing");
        }

        return new Jwt<>(
                payload,
                algorithmEnum,
                secret
        )
                .getToken();
    }
}
