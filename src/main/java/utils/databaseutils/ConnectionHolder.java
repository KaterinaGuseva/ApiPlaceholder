package utils.databaseutils;

import lombok.experimental.UtilityClass;
import org.sql2o.Sql2o;

@UtilityClass
public class ConnectionHolder {

    private static Sql2o sql2o;
    
    public static Sql2o getDataBaseConnection(String configName, String userName, String password) {
        if (sql2o == null){
            sql2o = new Sql2o(configName, userName, password);
        }
        return sql2o;
    }
}
