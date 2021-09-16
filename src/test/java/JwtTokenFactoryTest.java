import com.lukas8219.jwt.JwtToken;
import mock.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtTokenFactoryTest {

    @Test
    public void whenJwt_User_mustReturnValidJWT() {
        var user = new User("lucas", "123");
        var subject = JwtToken.generateToken(user);
        var decoded = JwtToken.decodeToken(subject, User.class);
        Assertions.assertEquals(user, decoded);
    }

    @Test
    public void whenJwt_String_mustReturnValidJWT(){
        var payload = "Este é meu payload";
        var subject = JwtToken.generateToken(payload);
        var decoded = JwtToken.decodeToken(subject, String.class);
        Assertions.assertEquals(payload, decoded);
    }

}
