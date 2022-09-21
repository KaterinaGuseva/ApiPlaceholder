package pojo.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Id;

import java.sql.Timestamp;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TestData {
    
    @Id
    private long id;
    private String name;
    @Column(name = "status_id")
    private int statusId;
    @Column(name = "method_name")
    private String methodName;
    @Column(name = "project_id")
    private long projectId;
    @Column(name = "session_id")
    private long sessionId;
    @Column(name = "start_time")
    private Timestamp startTime;
    @Column(name = "end_time")
    private Timestamp endTime;
    private String env;
    private String browser;
    @Column(name = "author_id")
    private long authorId;

    public TestData(int statusId, Timestamp startTime, Timestamp endTime) {
        this.statusId = statusId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
