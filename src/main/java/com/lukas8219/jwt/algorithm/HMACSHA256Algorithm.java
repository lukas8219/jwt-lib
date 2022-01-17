package com.lukas8219.jwt.algorithm;

import com.lukas8219.jwt.data.jwt.AlgorithmEnum;
import com.lukas8219.jwt.util.Base64String;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

class HMACSHA256Algorithm extends AbstractSignatureAlgorithm {

    private final AlgorithmEnum algorithm = AlgorithmEnum.HS256;

    public HMACSHA256Algorithm(byte[] secret) {
        super(secret);
    }

    public HMACSHA256Algorithm(String secret) {
        super(secret);
    }

    @Override
    public Base64String encode(Base64String base64Header, Base64String base64Payload) {
        try {
            var mac = Mac.getInstance(algorithm.getInstanceName());
            var message = formatMessage(base64Header, base64Payload);
            var key = new SecretKeySpec(getSecret(), algorithm.getInstanceName());
            mac.init(key);
            var result = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            return new Base64String(result);
        } catch (Exception e) {
            System.out.printf("An error occurred"); //Log it
            return new Base64String("");
        }
    }

    private String formatMessage(Base64String base64Header, Base64String base64Payload) {
        return String.format("%s.%s",
                base64Header,
                base64Payload);
    }

}
