package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.database.TestData;
import configuration.UnionReportingDataConfiguration;
import utils.databaseutils.UnionReportingDBUtils;
import data.TestStatus;

import java.sql.Timestamp;

public class TestProcessingDataFromDatabase extends BaseTestProcessingDataFromDatabase {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void executeSelectedTest1() {
        TestData newUpdateTest1 = TestData.builder()
                .statusId(TestStatus.FAILED.getValueTestStatus())
                .startTime(new Timestamp(System.currentTimeMillis()))
                .endTime(new Timestamp(System.currentTimeMillis()))
                .build();
        long idSelectedTest1 = newAddedId.get(UnionReportingDataConfiguration.getIndexSelectedTest1());
        UnionReportingDBUtils.updateStatusAndTimeTestById(
                idSelectedTest1, newUpdateTest1);
        TestData selectedTest1 = UnionReportingDBUtils.selectTestById(idSelectedTest1);
        softAssert.assertEquals(selectedTest1.getStatusId(), newUpdateTest1.getStatusId(),
                String.format("Entry with column status_id about test (id = %d) should be update",
                        idSelectedTest1));
        softAssert.assertEquals(selectedTest1.getStartTime(), newUpdateTest1.getStartTime(),
                String.format("Entry with column start_time about test (id = %d) should be update",
                        idSelectedTest1));
        softAssert.assertEquals(selectedTest1.getEndTime(), newUpdateTest1.getEndTime(),
                String.format("Entry with column start_time about test (id = %d) should be update",
                        idSelectedTest1));
    }

    @Test
    public void executeSelectedTest2() {
        TestData newUpdateTest2 = TestData.builder()
                .statusId(TestStatus.FAILED.getValueTestStatus())
                .startTime(new Timestamp(System.currentTimeMillis()))
                .endTime(new Timestamp(System.currentTimeMillis()))
                .build();
        long idSelectedTest2 = newAddedId.get(UnionReportingDataConfiguration.getIndexSelectedTest2());
        UnionReportingDBUtils.updateStatusAndTimeTestById(
                idSelectedTest2, newUpdateTest2);
        TestData selectedTest2 = UnionReportingDBUtils.selectTestById(idSelectedTest2);
        softAssert.assertEquals(selectedTest2.getStatusId(), newUpdateTest2.getStatusId(),
                String.format("Entry with column status_id about test (id = %d) should be update",
                        idSelectedTest2));
        softAssert.assertEquals(selectedTest2.getStartTime(), newUpdateTest2.getStartTime(),
                String.format("Entry with column start_time about test (id = %d) should be update",
                        idSelectedTest2));
        softAssert.assertEquals(selectedTest2.getEndTime(), newUpdateTest2.getEndTime(),
                String.format("Entry with column start_time about test (id = %d) should be update",
                        idSelectedTest2));
    }

    @Test
    public void executeSelectedTest3() {
        TestData newUpdateTest3 = TestData.builder()
                .statusId(TestStatus.SKIPPED.getValueTestStatus())
                .startTime(new Timestamp(System.currentTimeMillis()))
                .endTime(new Timestamp(System.currentTimeMillis()))
                .build();
        long idSelectedTest3 = newAddedId.get(UnionReportingDataConfiguration.getIndexSelectedTest3());
        UnionReportingDBUtils.updateStatusAndTimeTestById(
                idSelectedTest3, newUpdateTest3);
        TestData selectedTest3 = UnionReportingDBUtils.selectTestById(idSelectedTest3);
        softAssert.assertEquals(selectedTest3.getStatusId(), newUpdateTest3.getStatusId(),
                String.format("Entry with column status_id about test (id = %d) should be update",
                        idSelectedTest3));
        softAssert.assertEquals(selectedTest3.getStartTime(), newUpdateTest3.getStartTime(),
                String.format("Entry with column start_time about test (id = %d) should be update",
                        idSelectedTest3));
        softAssert.assertEquals(selectedTest3.getEndTime(), newUpdateTest3.getEndTime(),
                String.format("Entry with column start_time about test (id = %d) should be update",
                        idSelectedTest3));
    }
}
