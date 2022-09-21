package configuration;

import lombok.experimental.UtilityClass;
import utils.commonutils.JsonUtil;

@UtilityClass
public class TestApiDataConfiguration {

    private static final String PATH_TEST_DATA_JSON = "src/test/resources/test_api_data.json";

    private static String parseTestData(String keyJson) {
        return JsonUtil.parseJson(PATH_TEST_DATA_JSON, keyJson);
    }
    
    public static int getIdRequestTestCase2() {
        return Integer.parseInt(parseTestData("id_get_request_test_case2"));
    }

    public static int getIdRequestTestCase3() {
        return Integer.parseInt(parseTestData("id_get_request_test_case3"));
    }

    public static int getIdRequestTestCase6() {
        return Integer.parseInt(parseTestData("id_get_request_test_case6"));
    }

    public static int getUserIdTestCase2() {
        return Integer.parseInt(parseTestData("user_id_test_case2"));
    }

    public static int getIdTestCase2() {
        return Integer.parseInt(parseTestData("id_test_case2"));
    }

    public static String getBodyTestCase3() {
        return parseTestData("body_test_case3");
    }
    
    public static int getUserIdTestCase4() {
        return Integer.parseInt(parseTestData("user_id_test_case4"));
    }

    public static int getIdTestCase4() {
        return Integer.parseInt(parseTestData("id_test_case4"));
    }

    public static int getIdTestCase5() {
        return Integer.parseInt(parseTestData("user_get_id_test_case5"));
    }

    public static String getDataUserTestCase5() {
        return parseTestData("path_data_user_test_case5");
    }
}
