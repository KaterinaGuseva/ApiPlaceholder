package test;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pojo.database.Author;
import pojo.database.Project;
import pojo.database.TestData;
import configuration.TestApiConfiguration;
import utils.databaseutils.DataBaseUtils;
import utils.databaseutils.UnionReportingDBUtils;
import configuration.UnionReportingDataConfiguration;
import utils.apiutils.Specifications;

import java.sql.Timestamp;
import java.util.List;

public class BaseTestPlaceholder {

    private long idNewAuthor;
    private long idNewProject;
    private long idNewTest;

    @BeforeClass
    public void setUpRequestSpecification() {
        RestAssured.requestSpecification = Specifications.requestSpecification(TestApiConfiguration.getBaseUrl());
    }

    @BeforeClass
    public void addAuthorAndProject() {
        Author newAuthor = Author.builder()
                .name(UnionReportingDataConfiguration.getAuthorName())
                .login(UnionReportingDataConfiguration.getAuthorLogin())
                .email(UnionReportingDataConfiguration.getAuthorEmail())
                .build();
        Project newProject = Project.builder()
                .name(UnionReportingDataConfiguration.getProjectName())
                .build();
        List<Author> selectedAuthor = UnionReportingDBUtils.selectAuthorByLogin();
        if (selectedAuthor.isEmpty()) {
            idNewAuthor = UnionReportingDBUtils.insertNewAuthor(newAuthor);
        } else {
            idNewAuthor = selectedAuthor.get(DataBaseUtils.INDEX_FIRST_ELEMENT).getId();
        }
        List<Project> selectedProject = UnionReportingDBUtils.selectProjectByName();
        if (selectedProject.isEmpty()) {
            idNewProject = UnionReportingDBUtils.insertNewProject(newProject);
        } else {
            idNewProject = selectedProject.get(DataBaseUtils.INDEX_FIRST_ELEMENT).getId();
        }
    }
    
    @AfterMethod
    public void addTest(ITestResult result) {
        TestData newTest = TestData.builder()
                .name(result.getTestClass().getName())
                .statusId(result.getStatus())
                .methodName(result.getMethod().getMethodName())
                .projectId(idNewProject)
                .sessionId(UnionReportingDataConfiguration.getSessionId())
                .startTime(new Timestamp(result.getStartMillis()))
                .endTime(new Timestamp(result.getEndMillis()))
                .env(UnionReportingDataConfiguration.getProjectEnvironment())
                .browser(UnionReportingDataConfiguration.getBrowser())
                .authorId(idNewAuthor)
                .build();
        idNewTest = UnionReportingDBUtils.insertNewTest(newTest);
        Assert.assertEquals(UnionReportingDBUtils.selectTestById(idNewTest).getId(), idNewTest,
                String.format("Entry with information about executable test (id = %d) " +
                        "should be added to the database", idNewTest));
    }
}
