package utils.commonutils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
public class FileReaderUtils {

    public  static final String QUERY_FILE_PATH = "src/main/resources/queries/%s";
  
    public static String readQueryFile(String nameQueryFile) {
        try {
            return Files.readString(Paths.get(String.format(QUERY_FILE_PATH,
                            nameQueryFile)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
