package algorithm;

import jwt.JwtHeader;
import jwt.JwtPayload;

public abstract class SignatureAlgorithm {

    public abstract byte[] getSecret();

    public abstract Base64String encode(JwtHeader header, JwtPayload<?> payload);

}
