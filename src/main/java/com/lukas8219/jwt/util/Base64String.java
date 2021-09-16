package com.lukas8219.jwt.util;

import lombok.EqualsAndHashCode;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;

@EqualsAndHashCode
public class Base64String {

    @EqualsAndHashCode.Include
    private final String value;

    private final Encoder encoder = Base64.getUrlEncoder()
            .withoutPadding();

    private Base64String(String string, boolean isInternal) {
        if (isInternal) {
            value = string;
        } else {
            value = encoder.encodeToString(string.getBytes(StandardCharsets.UTF_8));
        }
    }

    public Base64String(String text) {
        this(text, false);
    }

    public Base64String(byte[] bytes) {
        value = encoder.encodeToString(bytes);
    }

    public static Base64String of(String encodedBase64) {
        return new Base64String(encodedBase64, true);
    }

    public String toString() {
        return value;
    }
}
