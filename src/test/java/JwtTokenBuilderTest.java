import com.lukas8219.jwt.JwtTokenBuilder;
import com.lukas8219.jwt.data.jwt.AlgorithmEnum;
import mock.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtTokenBuilderTest {

    private final String SECRET = "1234";

    public String buildJwt(Object payload, AlgorithmEnum algo) {
        return JwtTokenBuilder.newBuilder()
                .withPayload(payload)
                .withAlgorithm(algo == null ? AlgorithmEnum.HS256 : algo)
                .withSecret(SECRET)
                .build();
    }

    public String buildJwtWithHs256(Object payload) {
        return buildJwt(payload, null);
    }

    @Test
    public void whenBuild_Jwt_withHS256_mustReturn_Valid_JWT_String() {
        var user = new User("lucas", 123L);
        var token = buildJwtWithHs256(user);
        var expected = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Imx1Y2FzIiwiaWQiOjEyM30.izWiM5i92hazehO2e6RVZyu-E2NhqQfk-2JxIH3cIrg";
        Assertions.assertEquals(token, expected);
    }

}
