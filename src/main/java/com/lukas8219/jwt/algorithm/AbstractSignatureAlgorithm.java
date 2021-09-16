package com.lukas8219.jwt.algorithm;

import com.lukas8219.jwt.util.Base64String;

import java.nio.charset.StandardCharsets;

abstract class AbstractSignatureAlgorithm implements SignatureAlgorithm{

    @Override
    public byte[] getSecret() {
        return "80868c4abab7503b39176fbb475164402f17ce2adbf5e36a44f92a5285d3934a".getBytes(StandardCharsets.UTF_8);
    }

    public abstract Base64String encode(Base64String base64Header, Base64String base64Payload);

}
