package com.lukas8219.jwt.algorithm;

import com.lukas8219.jwt.data.jwt.AlgorithmEnum;

import java.util.HashMap;
import java.util.Map;

public final class SignatureAlgorithmFactory {

    private static final Map<AlgorithmEnum, SignatureAlgorithm> algorithms = createAlgorithm();

    private SignatureAlgorithmFactory() {
    }

    private static Map<AlgorithmEnum,SignatureAlgorithm> createAlgorithm() {
        Map<AlgorithmEnum,SignatureAlgorithm> map = new HashMap<>();
        map.put(AlgorithmEnum.HS256, new HMACSHA256Algorithm());
        return map;
    }

    public static SignatureAlgorithm getAlgorithm(AlgorithmEnum algorithmEnum) {
        if (algorithms.containsKey(algorithmEnum)) {
            return algorithms.get(algorithmEnum);
        } else {
            throw new IllegalArgumentException("Algorithm not implemented yet");
        }

    }

}
