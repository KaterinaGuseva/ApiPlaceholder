package utils.databaseutils;

import lombok.experimental.UtilityClass;
import pojo.database.Author;
import pojo.database.Project;
import pojo.database.TestData;
import configuration.UnionReportingDataConfiguration;
import data.Query;
import utils.commonutils.RandomUtils;

import java.util.List;

@UtilityClass
public class UnionReportingDBUtils {
    
    public static List<Author> selectAuthorByLogin() {
        return DataBaseUtils.selectEntries(Author.class,
                Query.SELECT_AUTHOR_BY_LOGIN.getStringQuery(UnionReportingDataConfiguration.getAuthorLogin()));
    }
    
    public static List<Project> selectProjectByName() {
        return DataBaseUtils.selectEntries(Project.class, 
                Query.SELECT_PROJECT_BY_NAME.getStringQuery(UnionReportingDataConfiguration.getProjectName()));
    }

    public static List<TestData> selectTestsById(long idTest) {
        return DataBaseUtils.selectEntries(TestData.class, 
                Query.SELECT_TEST_BY_ID.getStringQuery(idTest));
    }

    public static TestData selectTestById(long idTest) {
        return (DataBaseUtils.selectEntry(TestData.class,
                Query.SELECT_TEST_BY_ID.getStringQuery(idTest)));
    }
    
    public static List<TestData> selectThreeTestsWhereIdIsTwoRandomRepeatingDigits() {
        return DataBaseUtils.selectEntries(TestData.class, 
                Query.SELECT_TEST_BY_ID_IN.getStringQuery(
                        RandomUtils.generateRepeatingDigit(),
                        RandomUtils.generateRepeatingDigit(),
                        RandomUtils.generateRepeatingDigit()));
    }
  
    public static long insertNewAuthor(Object newAuthor) {
        return DataBaseUtils.insertNewEntry(Query.INSERT_AUTHOR.getStringQuery(), newAuthor);
    }

    public static long insertNewProject(Object newProject) {
        return DataBaseUtils.insertNewEntry(Query.INSERT_PROJECT.getStringQuery(), newProject);
    }
    
    public static long insertNewTest(Object newTest) {
        return DataBaseUtils.insertNewEntry(Query.INSERT_TEST.getStringQuery(), newTest);
    }
    
    public static void updateStatusAndTimeTestById(Long currentId, Object newUpdateTest) {
        DataBaseUtils.updateEntry(Query.UPDATE_TEST_BY_ID.getStringQuery(currentId), newUpdateTest);
    }
        
    public static void deleteTestById(long id) {
        DataBaseUtils.deleteSelectedEntry(
                Query.DELETE_TEST_BY_ID.getStringQuery(id));
    }
}
