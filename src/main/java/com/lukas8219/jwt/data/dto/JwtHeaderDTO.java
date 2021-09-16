package com.lukas8219.jwt.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lukas8219.jwt.data.jwt.AlgorithmEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtHeaderDTO {

    @JsonProperty("alg")
    private AlgorithmEnum algorithm;
    private String typ = "JWT";

}
