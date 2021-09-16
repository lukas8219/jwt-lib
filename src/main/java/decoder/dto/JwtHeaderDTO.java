package decoder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jwt.AlgorithmEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtHeaderDTO {

    @JsonProperty("alg")
    private AlgorithmEnum algorithm;
    private String typ = "JWT";

}
