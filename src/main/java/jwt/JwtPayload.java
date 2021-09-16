package jwt;

import algorithm.Base64String;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import util.ObjectMapperWrapper;

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
        return ObjectMapperWrapper.writeValueAsString(t);
    }

    public <T> T getBody() {
        var type = new TypeReference<T>(){};
        return ObjectMapperWrapper.readValue(body,type);
    }

}
