package jwt;

import algorithm.Base64String;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import util.ObjectMapperWrapper;

@Getter
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
        var json = ObjectMapperWrapper.writeValueAsString(this);
        return new Base64String(json);
    }

}
