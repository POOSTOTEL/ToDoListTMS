package src.com.tms.todo.list.java.model;

public enum Status {
    ACTIVE("This case is active!"),
    CANCELED("This case canceled..."),
    OVERDUE("This task is overdue :("),
    FINISHED("This case finished!");
    private String description;
    Status(String description) {
        this.description = description;
    }
}
