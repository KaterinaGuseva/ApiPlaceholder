package data;


import utils.commonutils.FileReaderUtils;

public enum Query {
    
    SELECT_PROJECT_BY_NAME(FileReaderUtils.readQueryFile("select_project_by_name.file")),
    SELECT_TEST_BY_ID(FileReaderUtils.readQueryFile("select_test_by_id.file")),
    SELECT_AUTHOR_BY_LOGIN(FileReaderUtils.readQueryFile("select_author_by_login.file")),
    DELETE_TEST_BY_ID(FileReaderUtils.readQueryFile("delete_test_by_id.file")),
    SELECT_TEST_BY_ID_IN(FileReaderUtils.readQueryFile("select_test_by_id_in.file")),
    INSERT_AUTHOR(FileReaderUtils.readQueryFile("insert_author.file")),
    INSERT_TEST(FileReaderUtils.readQueryFile("insert_test.file")),
    INSERT_PROJECT(FileReaderUtils.readQueryFile("insert_project.file")),
    UPDATE_TEST_BY_ID(FileReaderUtils.readQueryFile("update_test_by_id.file"));
    
    private String stringQuery;
    
    Query(String stringQuery) {
        this.stringQuery = stringQuery;
    }

    public String getStringQuery(Object...args) {
        return String.format(stringQuery, args);
    }
}
