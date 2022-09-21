package utils.commonutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.FileReader;
import java.io.IOException;

@UtilityClass
public class JsonUtil {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T deserialization(String content, Class<T> name) {
        try {
            return objectMapper.readValue(content, name);
        } catch (JsonProcessingException e) {
              throw new RuntimeException(e);
        }
    }
    
    public static <T> T readJsonFromFileToObject(String path, Class<T> namePogoClass) {
        try {
            return objectMapper.readValue(new FileReader(path), namePogoClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String parseJson(String pathJson, String value) {
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(new FileReader(pathJson));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonNode.get(value).asText();
    }
}
