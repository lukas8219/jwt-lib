package decoder;

import com.fasterxml.jackson.core.type.TypeReference;
import decoder.dto.JwtHeaderDTO;
import decoder.dto.JwtSignatureDTO;
import lombok.Getter;
import util.Base64Decoder;
import util.ObjectMapperWrapper;

@Getter
public class JwtDecoder<T> {

    private JwtHeaderDTO header;
    private T payload;
    private JwtSignatureDTO signature;

    @Deprecated
    public JwtDecoder(String token) {
        var parts = separateIntoParts(token);
        this.header = toHeader(parts[0]);
        this.payload = toPayload(parts[1]);
        this.signature = toSignature(parts[2]);
    }

    private static String[] separateIntoParts(String token) {
        if (token == null) {
            throw new NullPointerException("Token cannot be null");
        }
        if (token.indexOf(".") == token.lastIndexOf(".")) {
            throw new IllegalArgumentException("JWT Token need two dots '.'");
        }
        var parts = token.split("[.]");
        if (parts.length != 3) {
            var errorMessage = String.format("Expected 3 parts, but actual: %d", parts.length);
            throw new IllegalArgumentException(errorMessage);
        }
        return parts;
    }

    public static <N> N getPayloadFromToken(String token, Class<N> tClass) {
        var parts = separateIntoParts(token);
        var payloadJson = Base64Decoder.decode(parts[1]);
        return ObjectMapperWrapper.readValue(payloadJson, tClass);
    }

    private JwtSignatureDTO toSignature(String base64Header) {
        var json = Base64Decoder.decode(base64Header);
        return ObjectMapperWrapper.readValue(json, JwtSignatureDTO.class);
    }

    private <T> T toPayload(String base64Payload) {
        var json = Base64Decoder.decode(base64Payload);
        var typeReference = new TypeReference<T>() {};
        return ObjectMapperWrapper.readValue(json, typeReference); // TODO adjust it
    }

    private JwtHeaderDTO toHeader(String base64Header) {
        var json = Base64Decoder.decode(base64Header);
        return ObjectMapperWrapper.readValue(json, JwtHeaderDTO.class);
    }

}
