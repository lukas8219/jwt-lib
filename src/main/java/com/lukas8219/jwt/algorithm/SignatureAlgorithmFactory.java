package com.lukas8219.jwt.algorithm;

import com.lukas8219.jwt.data.jwt.AlgorithmEnum;

import java.security.Signer;
import java.util.HashMap;
import java.util.Map;

public final class SignatureAlgorithmFactory {


    private final Map<AlgorithmEnum, SignatureAlgorithm> algorithms;

    private final String secret;

    public static SignatureAlgorithmFactory of(String secret){
        return new SignatureAlgorithmFactory(secret);
    }

    private SignatureAlgorithmFactory(String secret) {
        this.secret = secret;
        this.algorithms = createAlgorithm();
    }

    private Map<AlgorithmEnum,SignatureAlgorithm> createAlgorithm() {
        Map<AlgorithmEnum,SignatureAlgorithm> map = new HashMap<>();
        map.put(AlgorithmEnum.HS256, new HMACSHA256Algorithm(secret));
        return map;
    }

    public SignatureAlgorithm getAlgorithm(AlgorithmEnum algorithmEnum) {
        if (algorithms.containsKey(algorithmEnum)) {
            return algorithms.get(algorithmEnum);
        } else {
            throw new IllegalArgumentException("Algorithm not implemented yet");
        }

    }

}
