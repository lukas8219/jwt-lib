package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ObjectMapperWrapper {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static boolean isConfigured = false;

    private ObjectMapperWrapper(){
    }

    public static String writeValueAsString(Object object){
        if(!isConfigured){
            configure();
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e){
            return "";
        }
    }

    public static <T> T readValue(String text, Class<T> tClass){
        return readValue(text.getBytes(StandardCharsets.UTF_8), tClass);
    }

    public static <T> T readValue(byte[] bytes, Class<T> tClass){
        try {
            return mapper.readValue(bytes, tClass);
        } catch (IOException e) {
            System.out.println("An error occurred when trying to read "+new String(bytes));
            return null;
        }
    }

    public static <T> T readValue(String text, TypeReference<T> tTypeReference){
        try {
            return mapper.readValue(text, tTypeReference);
        } catch (JsonProcessingException e) {
            System.out.println("An error occurred when trying to read "+text);
            return null;
        }
    }

    private static void configure() {
        //implement if needed
    }
}
