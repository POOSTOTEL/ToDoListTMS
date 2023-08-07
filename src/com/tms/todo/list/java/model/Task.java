package src.com.tms.todo.list.java.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Task implements Serializable {
    public static int maxTaskNumber;
    protected int number;
    private String name;
    private String description;
    private LocalDateTime timeOfCreation;
    private LocalDateTime editTime;
    private LocalDateTime completionTime;
    protected LocalDateTime deadLine;
    private Status status;
    public Task(String name, String description) {
        this.number = ++maxTaskNumber;
        try {
            if (name.length() < 25) {
                this.name = name;
            } else {
                throw new ValidationException("The name is too long, try shorter.");
            }
        }   catch (ValidationException e) {
            System.out.println(e.toString());
        }
        try {
            if (name.length() < 100) {
                this.description = description;
            } else {
                throw new ValidationException("The description is too long, try shorter.");
            }
        }   catch (ValidationException e) {
            System.out.println(e.toString());
        }
        this.timeOfCreation = LocalDateTime.now();
        this.editTime = this.timeOfCreation;
        this.status = Status.ACTIVE;
    }
    public Task(String name, String description, LocalDateTime deadLine) {
        this(name, description);
        this.deadLine = deadLine;
        if (timeOfCreation.isAfter(this.deadLine)) {
            this.status = Status.OVERDUE;
        }
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public int getNumber() {
        return number;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String shortToString() {
        return "Task{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    @Override
    public String toString() {
        return "Task{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", timeOfCreation=" + timeOfCreation +
                ", editTime=" + editTime +
                ", completionTime=" + completionTime +
                ", deadLine=" + deadLine +
                ", status=" + status +
                '}';
    }
}
