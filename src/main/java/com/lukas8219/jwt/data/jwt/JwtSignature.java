package com.lukas8219.jwt.data.jwt;

import com.lukas8219.jwt.util.Base64String;
import com.lukas8219.jwt.algorithm.SignatureAlgorithmFactory;
import lombok.Getter;

@Getter
public class JwtSignature {

    private Base64String base64;

    public JwtSignature(JwtHeader header, JwtPayload<?> payload) {
        var algo = header.getAlgorithm();
        var algorithm = SignatureAlgorithmFactory.getAlgorithm(algo);
        base64 = algorithm.encode(header.getBase64(), payload.getBase64());
    }

}
