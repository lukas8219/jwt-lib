package com.lukas8219.jwt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonMapper {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static boolean isConfigured = false;

    private JsonMapper(){
    }

    public static String writeValueAsString(Object object){
        if(!isConfigured){
            configure();
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e.getMessage());
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
        isConfigured = true;
    }
}
