import com.lukas8219.jwt.JwtToken;
import mock.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class JwtTokenFactoryTest {

    private final String secret = "1234";

    @Test
    public void whenJwt_User_mustReturnValidJWT() {
        var user = new User("lucas", 123L);
        var subject = JwtToken.generateToken(user, secret);
        var decoded = JwtToken.decodeToken(subject, User.class, secret);
        Assertions.assertEquals(user, decoded);
    }

    @Test
    public void whenJwt_String_mustReturnValidJWT() {
        var payload = "Este é meu payload";
        var subject = JwtToken.generateToken(payload,secret);
        var decoded = JwtToken.decodeToken(subject, String.class,secret);
        Assertions.assertEquals(payload, decoded);
    }

    @Test
    public void generateJwt() {
        var user = new User(13L);
        var subject = JwtToken.generateToken(user, secret);
        System.out.println(subject);
        var decoded = JwtToken.decodeToken(subject, User.class,secret);
        Assertions.assertEquals(user, decoded);
    }

    @Test
    @Disabled
    public void whenGenerateJWT_mustReturnProper_JWT() {
        var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Imx1Y2FzIiwiaWQiOjF9.RFFbjpM3-JMlJicwoOrNlCgPhn-04yba0udpKfy8E5A";
        var generated = JwtToken.generateToken(new User("lucas", 1L), secret);
        Assertions.assertEquals(token, generated);
    }
}
