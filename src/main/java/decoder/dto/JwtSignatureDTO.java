package decoder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtSignatureDTO {

    @JsonProperty("base64")
    private String encoded;

}
