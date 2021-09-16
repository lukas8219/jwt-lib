package jwt;

import algorithm.Base64String;
import algorithm.SignatureAlgorithm;
import factory.SignatureAlgorithmFactory;
import lombok.Getter;

public class JwtSignature {

    private final SignatureAlgorithm algorithm;
    @Getter
    private Base64String base64;

    public JwtSignature(JwtHeader header, JwtPayload<?> payload) {
        var algo = header.getAlgorithm();
        algorithm = SignatureAlgorithmFactory.getAlgorithm(algo);
        base64 = algorithm.encode(header, payload);
    }

}
