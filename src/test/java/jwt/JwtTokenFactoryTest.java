package jwt;

import decoder.JwtDecoder;
import factory.JwtTokenFactory;
import mock.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtTokenFactoryTest {

    @Test
    public void whenJwt_User_mustReturnValidJWT() {
        var user = new User("lucas", "123");
        var subject = JwtTokenFactory.generateToken(user);
        var decoded = JwtTokenFactory.decodeToken(subject, User.class);
        Assertions.assertEquals(user, decoded);
    }

    @Test
    public void whenJwt_User_mustDecodeProperly() {
        var user = new User("lucas", "123");
        var subject = JwtTokenFactory.generateToken(user);
        var decoded = new JwtDecoder<User>(subject);
        Assertions.assertEquals(user, decoded.getPayload());
      // TODO Casting Payload NOT WORKING
    }
}
