package utils.databaseutils;

import lombok.experimental.UtilityClass;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import configuration.UnionReportingCredentialsConfiguration;

import java.math.BigInteger;
import java.util.List;

@UtilityClass
public class DataBaseUtils {

    public static final int INDEX_FIRST_ELEMENT = 0;
    
    private Sql2o sql2o = ConnectionHolder.getDataBaseConnection
            (String.format("%s/%s", UnionReportingCredentialsConfiguration.getDBLocalhost(), 
                    UnionReportingCredentialsConfiguration.getDBName()), 
                    UnionReportingCredentialsConfiguration.getDBUser(), 
                    UnionReportingCredentialsConfiguration.getDBPassword());
 
    public static <T> List<T> selectEntries(Class<T> namePogoClass, String sqlSelectQuery) {
        try(Connection con = sql2o.open()) {
            return con.createQuery(sqlSelectQuery).executeAndFetch(namePogoClass);
        }
    }

    public static <T> T selectEntry(Class<T> namePogoClass, String sqlSelectQuery) {
        try(Connection con = sql2o.open()) {
            return con.createQuery(sqlSelectQuery)
                    .executeAndFetch(namePogoClass).get(INDEX_FIRST_ELEMENT);
        }
    }
    
    public static long insertNewEntry(String sqlInsertQuery, Object newObject) {
        try (Connection con = sql2o.open()) {
            BigInteger insertedIdInt = (BigInteger) con.createQuery(sqlInsertQuery).bind(newObject).executeUpdate()
                    .getKey();
            return insertedIdInt.longValue();
        }
    }
    
    public static void updateEntry(String sqlUpdateQuery, Object className) {
        try (Connection con = sql2o.open()) {
            con.createQuery(sqlUpdateQuery).bind(className).executeUpdate();             
        }
    }

    public static void deleteSelectedEntry(String sqlDeleteQuery) {
        try (Connection con = sql2o.open()) {
            con.createQuery(sqlDeleteQuery).executeUpdate();
        }
    }
}
