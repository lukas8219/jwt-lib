package algorithm;

import jwt.AlgorithmEnum;
import jwt.JwtHeader;
import jwt.JwtPayload;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class HMACSHA256Algorithm extends SignatureAlgorithm {

    private final AlgorithmEnum algorithm = AlgorithmEnum.HS256;

    @Override
    public byte[] getSecret() {
        return "80868c4abab7503b39176fbb475164402f17ce2adbf5e36a44f92a5285d3934a".getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Base64String encode(JwtHeader header, JwtPayload<?> payload) {
        try {
            var mac = Mac.getInstance(algorithm.getInstanceName());
            var message = String.format("%s.%s",
                    header.getBase64(),
                    payload.getBase64());
            var key = new SecretKeySpec(getSecret(), algorithm.getInstanceName());
            mac.init(key);
            var result = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            return new Base64String(result);
        } catch (Exception e) {
            System.out.printf("An error occurred"); //Log it
            return new Base64String("");
        }
    }
}
