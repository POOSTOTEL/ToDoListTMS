package src.com.tms.todo.list;

import src.com.tms.todo.list.java.model.ConsoleInfoPrinter;
import src.com.tms.todo.list.java.model.Task;
import src.com.tms.todo.list.java.model.ToDo;
import src.com.tms.todo.list.java.service.Validator;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TodoListRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean todoListIsLaunched = true;
        ToDo todo = new ToDo();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        while (todoListIsLaunched) {
            ConsoleInfoPrinter.printMenu();
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1 -> {
                    ConsoleInfoPrinter.printTypeOfAddedTask();
                    boolean hasDeadline = false;
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1 -> {
                            hasDeadline = true;
                        }
                        case 2 -> {
                            hasDeadline = false;
                        }
                        default -> System.out.println("Incorrect input data.");
                    }
                    String name;
                    do {
                        ConsoleInfoPrinter.printNameInputInformation();
                        name = scanner.nextLine();
                    } while (!Validator.validateName(name));
                    String description;
                    do {
                        ConsoleInfoPrinter.printDescriptionInputInformation();
                        description = scanner.nextLine();
                    } while (!Validator.validateDescription(description));
                    if (hasDeadline) {
                        ConsoleInfoPrinter.printDateInputInformation();
                        String userDate = scanner.nextLine();
                        todo.addTask(new Task(name, description, LocalDateTime.parse(userDate, formatter)));
                    } else {
                        todo.addTask(new Task(name, description));
                    }
                }
                case 0 -> {
                    todoListIsLaunched = false;
                }
                default -> System.out.println("Incorrect input data. Try once more.");
            }
        }
    }
}
