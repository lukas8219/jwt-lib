package jwt;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlgorithmEnum {

    HS256("HmacSHA256");

    private final String instanceName;

}
