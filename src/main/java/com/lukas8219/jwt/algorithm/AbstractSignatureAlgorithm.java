package com.lukas8219.jwt.algorithm;

import com.lukas8219.jwt.util.Base64String;

import java.nio.charset.StandardCharsets;

abstract class AbstractSignatureAlgorithm implements SignatureAlgorithm {

    private final byte[] secret;

    public AbstractSignatureAlgorithm(byte[] secret) {
        this.secret = secret;
    }

    public AbstractSignatureAlgorithm(String secret) {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] getSecret() {
        return secret;
    }

    public abstract Base64String encode(Base64String base64Header, Base64String base64Payload);

}
