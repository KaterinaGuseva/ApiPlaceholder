package configuration;

import lombok.experimental.UtilityClass;
import utils.commonutils.JsonUtil;

@UtilityClass
public class TestApiConfiguration {

    private static final String PATH_TEST_CONFIG_JSON = "src/test/resources/test_api_configuration.json";

    private static String parseConfigData(String keyJson) {
        return JsonUtil.parseJson(PATH_TEST_CONFIG_JSON, keyJson);
    }

    public static String getBaseUrl() {
        return parseConfigData("base_url");
    }
}
