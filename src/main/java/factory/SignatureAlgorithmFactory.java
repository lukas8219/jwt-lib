package factory;

import algorithm.HMACSHA256Algorithm;
import algorithm.SignatureAlgorithm;
import jwt.AlgorithmEnum;

import java.util.HashMap;
import java.util.Map;

public class SignatureAlgorithmFactory {

    private static final Map<AlgorithmEnum, ? super SignatureAlgorithm> algorithms = createAlgorithm();

    private SignatureAlgorithmFactory() {
    }

    private static Map<AlgorithmEnum, ? super SignatureAlgorithm> createAlgorithm() {
        Map<AlgorithmEnum, ? super SignatureAlgorithm> map = new HashMap<>();
        map.put(AlgorithmEnum.HS256, new HMACSHA256Algorithm());
        return map;
    }

    public static SignatureAlgorithm getAlgorithm(AlgorithmEnum algorithmEnum){
        if(algorithms.containsKey(algorithmEnum)){
            return (SignatureAlgorithm) algorithms.get(algorithmEnum);
        } else {
            throw new IllegalArgumentException("Algorithm not implemented yet");
        }

    }

}
