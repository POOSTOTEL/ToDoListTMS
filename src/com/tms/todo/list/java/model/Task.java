package src.com.tms.todo.list.java.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task implements Serializable {
    private static int amountOfCases;
    private int number;
    private String name;
    private String description;
    private LocalDateTime timeOfCreation;
    private LocalDateTime editTime;
    private LocalDateTime completionTime;
    private LocalDateTime deadLine;
    private Status status;
    public Task(String name, String description) {
        this.number = ++amountOfCases;
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
    }
    public Task(String name, String description, LocalDateTime deadLine) {
        this(name, description);
        this.deadLine = deadLine;
        if (timeOfCreation.isBefore(this.deadLine)) {
            this.status = Status.ACTIVE;
        } else {
            this.status = Status.OVERDUE;
        }
    }
}
