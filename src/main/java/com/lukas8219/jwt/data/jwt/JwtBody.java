package com.lukas8219.jwt.data.jwt;

public class JwtBody<T> {

    private JwtHeader header;
    private JwtPayload<T> payload;
    private JwtSignature signature;

    public JwtBody(JwtHeader header, JwtPayload<T> payload, JwtSignature signature) {
        this.header = header;
        this.payload = payload;
        this.signature = signature;
    }

    public String getJwt(){
        return String.format("%s.%s.%s",
                header.getBase64(),
                payload.getBase64(),
                signature.getBase64());
    }

}