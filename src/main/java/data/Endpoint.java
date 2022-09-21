package data;

public enum Endpoint {
    
     POSTS("/posts"),
     USERS("/users");
     
     private String basePath;

    Endpoint(String basePath) {
        this.basePath = basePath;
    }

    public String getValue() {
        return basePath;
    }

    public String getValue(int id) {
        return String.format("%s/%d", basePath, id);
    }
}
