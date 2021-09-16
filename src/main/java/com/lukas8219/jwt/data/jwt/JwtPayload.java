package com.lukas8219.jwt.data.jwt;

import com.lukas8219.jwt.util.Base64String;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import com.lukas8219.jwt.util.JsonMapper;

import java.nio.charset.StandardCharsets;

public class JwtPayload<T> {

    private final String body;
    @Getter
    private final Base64String base64;

    public JwtPayload(T t) {
        body = initialize(t);
        base64 = new Base64String(body.getBytes(StandardCharsets.UTF_8));
    }

    private String initialize(T t) {
        return JsonMapper.writeValueAsString(t);
    }

    public <T> T getBody() {
        var type = new TypeReference<T>(){};
        return JsonMapper.readValue(body,type);
    }

}
