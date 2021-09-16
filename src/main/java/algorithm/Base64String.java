package algorithm;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;

public class Base64String {

    private final String value;
    private final Encoder encoder = Base64.getUrlEncoder()
            .withoutPadding();

    public Base64String(String text) {
        value = encoder.encodeToString(text.getBytes(StandardCharsets.UTF_8));
    }

    public Base64String(byte[] bytes) {
        value = encoder.encodeToString(bytes);
    }

    public String toString() {
        return value;
    }
}
