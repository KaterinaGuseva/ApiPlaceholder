package test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.databaseutils.DataBaseUtils;
import utils.databaseutils.UnionReportingDBUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseTestProcessingDataFromDatabase {
    
    protected List<Long> newAddedId = new ArrayList<>();
    private long idCurrentAuthor;
    private long idCurrentProject;

    @BeforeTest
    public void getSelectedTests() {
        idCurrentAuthor = UnionReportingDBUtils.selectAuthorByLogin()
                .get(DataBaseUtils.INDEX_FIRST_ELEMENT).getId();
        idCurrentProject = UnionReportingDBUtils.selectProjectByName()
                .get(DataBaseUtils.INDEX_FIRST_ELEMENT).getId();
        UnionReportingDBUtils.selectThreeTestsWhereIdIsTwoRandomRepeatingDigits()
                .forEach(test -> {
                    test.setProjectId(idCurrentProject);
                    test.setAuthorId(idCurrentAuthor);
                    newAddedId.add(UnionReportingDBUtils.insertNewTest(test));
                });
    }
    
    @AfterTest
    public void deleteSelectedEntry() {
        for (int i = 0; i < newAddedId.size(); i++) {
            UnionReportingDBUtils.deleteTestById(newAddedId.get(i));
        Assert.assertTrue(UnionReportingDBUtils.selectTestsById(newAddedId.get(i)).isEmpty(), 
                String.format("Entry with information about test (id = %d) " +
                    "should be deleted to the database", newAddedId.get(i)));
        }
    }
}
