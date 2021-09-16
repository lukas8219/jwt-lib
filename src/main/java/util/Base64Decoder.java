package util;

import java.util.Base64;
import java.util.Base64.Decoder;

public class Base64Decoder {

    private final static Decoder decoder = Base64.getUrlDecoder();

    private Base64Decoder(){
    }

    public static String decode(String base64Encoded){
        return new String(decoder.decode(base64Encoded));
    }
}
