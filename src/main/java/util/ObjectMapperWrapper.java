package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        try {
            return mapper.readValue(text, tClass);
        } catch (JsonProcessingException e) {
            System.out.println("An error occurred when trying to read "+text);
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
