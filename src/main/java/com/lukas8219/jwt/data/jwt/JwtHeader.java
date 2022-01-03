package com.lukas8219.jwt.data.jwt;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.lukas8219.jwt.util.Base64String;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.lukas8219.jwt.util.JsonMapper;

@Getter
@JsonPropertyOrder(alphabetic = true)
public class JwtHeader {

    @JsonProperty("alg")
    private final AlgorithmEnum algorithm;
    private final String typ = "JWT";
    @JsonIgnore
    @Getter
    private final Base64String base64;

    public JwtHeader(AlgorithmEnum algorithm) {
        this.algorithm = algorithm;
        base64 = getAsBase64();
    }

    private Base64String getAsBase64() {
        var json = JsonMapper.writeValueAsString(this);
        return new Base64String(json);
    }

}
