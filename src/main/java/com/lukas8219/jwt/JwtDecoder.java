package com.lukas8219.jwt;

import com.lukas8219.jwt.util.Base64String;
import com.lukas8219.jwt.data.dto.JwtHeaderDTO;
import com.lukas8219.jwt.algorithm.SignatureAlgorithmFactory;
import lombok.Getter;
import com.lukas8219.jwt.util.Base64Decoder;
import com.lukas8219.jwt.util.JsonMapper;

@Getter
class JwtDecoder {

    private JwtDecoder() {
    }

    public static <N> N getPayloadFromToken(String token, Class<N> tClass) {
        var parts = separateIntoParts(token);
        if (isSigned(parts)) {
            var payloadJson = Base64Decoder.decode(parts[1]);
            return JsonMapper.readValue(payloadJson, tClass);
        } else {
            throw new IllegalArgumentException("The payload is not verified/signed");
        }
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

    private static <N> boolean isSigned(String[] parts) {
        var headerDTO = getJwtHeader(parts[0]);
        var headerBase = Base64String.of(parts[0]);
        var payloadBase = Base64String.of(parts[1]);
        var algo = SignatureAlgorithmFactory.getAlgorithm(headerDTO.getAlgorithm());
        var encoded = algo.encode(headerBase, payloadBase).toString();
        return parts[2].equals(encoded);
    }

    private static JwtHeaderDTO getJwtHeader(String part) {
        var json = Base64Decoder.decode(part);
        return JsonMapper.readValue(json, JwtHeaderDTO.class);
    }

}
