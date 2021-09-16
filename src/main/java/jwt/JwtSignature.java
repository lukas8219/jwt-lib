package jwt;

import algorithm.Base64String;
import factory.SignatureAlgorithmFactory;
import lombok.Getter;

@Getter
public class JwtSignature {

    private Base64String base64;

    public JwtSignature(JwtHeader header, JwtPayload<?> payload) {
        var algo = header.getAlgorithm();
        var algorithm = SignatureAlgorithmFactory.getAlgorithm(algo);
        base64 = algorithm.encode(header, payload);
    }

}
