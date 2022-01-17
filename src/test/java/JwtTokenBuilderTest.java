import com.lukas8219.jwt.JwtTokenBuilder;
import com.lukas8219.jwt.data.jwt.AlgorithmEnum;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JwtTokenBuilderTest {

    @Test
    public void whenBuild(){
        var token = JwtTokenBuilder.newBuilder()
                .withPayload("DKASODKSODAS")
                .withAlgorithm(AlgorithmEnum.HS256)
                .withSecret("1234")
                .withClaim(Map.of("Tal", "Tal"))
                .build();
    }
}
