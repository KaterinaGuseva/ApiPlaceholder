package data;

public enum TestStatus {
    
    PASSED(1),
    FAILED(2),
    SKIPPED(3);
    
    private int valueTestStatus;

    TestStatus(int valueTestStatus) {
        this.valueTestStatus = valueTestStatus;
    }

    public int getValueTestStatus() {
        return valueTestStatus;
    }
}
