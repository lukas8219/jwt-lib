package decoder;

import com.fasterxml.jackson.core.type.TypeReference;
import decoder.dto.JwtHeaderDTO;
import decoder.dto.JwtSignatureDTO;
import factory.SignatureAlgorithmFactory;
import jwt.JwtHeader;
import jwt.JwtPayload;
import lombok.Getter;
import util.Base64Decoder;
import util.ObjectMapperWrapper;

import java.nio.charset.StandardCharsets;

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
        var payload = ObjectMapperWrapper.readValue(payloadJson, tClass);
        if(isSigned(parts, payload)){
            return payload;
        } else {
            throw new IllegalArgumentException("The payload is not verified/signed");
        }
    }

    private static <N> boolean isSigned(String[] parts, N payload) {
        var json = Base64Decoder.decode(parts[0]);
        var headerDTO = ObjectMapperWrapper.readValue(json, JwtHeaderDTO.class);
        var jwtHeader = new JwtHeader(headerDTO.getAlgorithm());
        var jwtPayload = new JwtPayload<>(payload);
        var algorithm = jwtHeader.getAlgorithm();
        var algo = SignatureAlgorithmFactory.getAlgorithm(algorithm);
        var encoded = algo.encode(jwtHeader, jwtPayload).toString();
        return parts[2].equals(encoded);
    }

    private JwtSignatureDTO toSignature(String base64Header) {
        var json = Base64Decoder.decode(base64Header);
        return ObjectMapperWrapper.readValue(json, JwtSignatureDTO.class);
    }

    private <T> T toPayload(String base64Payload) {
        var json = Base64Decoder.decode(base64Payload);
        var typeReference = new TypeReference<T>() {
        };
        return ObjectMapperWrapper.readValue(json, typeReference); // TODO adjust it
    }

    private JwtHeaderDTO toHeader(String base64Header) {
        var json = Base64Decoder.decode(base64Header);
        return ObjectMapperWrapper.readValue(json, JwtHeaderDTO.class);
    }

}
