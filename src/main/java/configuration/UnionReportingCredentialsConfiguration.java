package configuration;

import lombok.experimental.UtilityClass;
import utils.commonutils.JsonUtil;

@UtilityClass
public class UnionReportingCredentialsConfiguration {

    private static final String PATH_DATA_BASE_REG_JSON =
            "src/test/resources/union_reporting_database_credentials.json";

    private static String parseRegistrationData(String keyJson) {
        return JsonUtil.parseJson(PATH_DATA_BASE_REG_JSON, keyJson);
    }
    
    public static String getDBLocalhost() {
        return parseRegistrationData("localhost");
    }

    public static String getDBName() {
        return parseRegistrationData("name");
    }

    public static String getDBUser() {
        return parseRegistrationData("user");
    }

    public static String getDBPassword() {
        return parseRegistrationData("password");
    }
}
