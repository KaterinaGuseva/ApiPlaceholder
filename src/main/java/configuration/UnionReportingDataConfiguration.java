package configuration;

import lombok.experimental.UtilityClass;
import utils.commonutils.JsonUtil;

@UtilityClass
public class UnionReportingDataConfiguration {

    private static final String PATH_DATA_BASE_DATA_JSON =
            "src/test/resources/union_reporting_database_test_data.json";

    private static String parseTestData(String keyJson) {
        return JsonUtil.parseJson(PATH_DATA_BASE_DATA_JSON, keyJson);
    }
    
    public static String getAuthorName() {
        return parseTestData("author_name");
    }

    public static String getAuthorLogin() {
        return parseTestData("author_login");
    }

    public static String getAuthorEmail() {
        return parseTestData("author_email");
    }

    public static String getProjectName() {
        return parseTestData("project_name");
    }

    public static int getSessionId() {
        return Integer.parseInt(parseTestData("session_id"));
    }

    public static String getProjectEnvironment() {
        return parseTestData("project_environment");
    }

    public static String getBrowser() {
        return parseTestData("browser");
    }

    public static int getIndexSelectedTest1() {
        return Integer.parseInt(parseTestData("index_test1_from_test_case2"));
    }

    public static int getIndexSelectedTest2() {
        return Integer.parseInt(parseTestData("index_test2_from_test_case2"));
    }

    public static int getIndexSelectedTest3() {
        return Integer.parseInt(parseTestData("index_test3_from_test_case2"));
    }
}
