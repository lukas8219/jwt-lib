package com.lukas8219.jwt.algorithm;

import com.lukas8219.jwt.util.Base64String;

public interface SignatureAlgorithm {

    byte[] getSecret();

    Base64String encode(Base64String base64Header, Base64String base64Payload);

}
