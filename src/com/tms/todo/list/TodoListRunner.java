package src.com.tms.todo.list;

import src.com.tms.todo.list.java.model.ConsoleInfoPrinter;
import src.com.tms.todo.list.java.model.Status;
import src.com.tms.todo.list.java.model.Task;
import src.com.tms.todo.list.java.model.ToDo;
import src.com.tms.todo.list.java.service.Validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TodoListRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        boolean todoListIsLaunched = true;
        ToDo todo = new ToDo();
        todo.createFile();
        todo.deserializingListOfTasksFromFile();
        Task task1 = new Task("ABCDEFG", "Translate some words about dogs.");
        Task task2 = new Task("IFHIJKL", "Input much words about dogs.");
        Task task3 = new Task("MNOPQRS", "Output lazy words about dogs.", LocalDateTime.parse("2004-12-26 12:03", formatter));
        Task task4 = new Task("TUXYZAB", "Print a lot of words about dogs.");
        Task task5 = new Task("1234567", "Delete some words about dogs.", LocalDateTime.parse("2022-11-14 16:18", formatter));
        todo.addTask(task1);
        todo.addTask(task2);
        todo.addTask(task3);
        todo.addTask(task4);
        todo.addTask(task5);
        while (todoListIsLaunched) {
            ConsoleInfoPrinter.printMenu();
            //todo разобраться с тем, что парсер ломает всю защиту от дебилов
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
                        todo.addTask(new Task(name, description, LocalDateTime.parse(scanner.nextLine(), formatter)));
                    } else {
                        todo.addTask(new Task(name, description));
                    }
                }
                case 2 -> {
                    ConsoleInfoPrinter.printEditOptions();
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1 -> {
                            ConsoleInfoPrinter.printRequestForInputReplaceableData();
                            todo.editTaskName(Integer.parseInt(scanner.nextLine()), scanner.nextLine());
                        }
                        case 2 -> {

                            ConsoleInfoPrinter.printRequestForInputReplaceableData();
                            todo.editTaskDescription(Integer.parseInt(scanner.nextLine()), scanner.nextLine());
                        }
                        default -> System.out.println("Incorrect input data.");
                    }
                }
                case 3 -> {
                    ConsoleInfoPrinter.printRequestForInputNumber();
                    todo.setCanceledStatus(Integer.parseInt(scanner.nextLine()));
                }
                case 4 -> {
                    ConsoleInfoPrinter.printRequestForInputNumber();
                    todo.setFinishedStatus(Integer.parseInt(scanner.nextLine()));
                }
                case 5 -> {
                    ConsoleInfoPrinter.printRequestForInputNumber();
                    todo.deleteTask(Integer.parseInt(scanner.nextLine()));
                }
                case 6 -> {
                    todo.printShortInfoAboutTasks();
                }
                case 7 -> {
                    ConsoleInfoPrinter.printRequestForInputNumber();
                    todo.printTask(Integer.parseInt(scanner.nextLine()));
                }
                case 8 -> {
                    ConsoleInfoPrinter.printRequestForInputStatus();
                    Status status;
                    switch (scanner.nextLine()) {
                        case "ACTIVE" -> status = Status.ACTIVE;
                        case "CANCELED" -> status = Status.CANCELED;
                        case "OVERDUE" -> status = Status.OVERDUE;
                        case "FINISHED" -> status = Status.FINISHED;
                        default -> {
                            status = null;
                            System.out.println("Incorrect input ;(");
                        }
                    }
                    todo.printFilteredByStatus(status);
                }
                case 9 -> {
                    ConsoleInfoPrinter.printDateInputInformation();
                    todo.printFilteredByFinishTime(LocalDateTime.parse(scanner.nextLine(), formatter));
                }
                case 10 -> {
                    ConsoleInfoPrinter.printRequestForInputSearchPhrase();
                    todo.searchTaskByName(scanner.nextLine());
                }
                case 0 -> {
                    todoListIsLaunched = false;
                }
                default -> System.out.println("Incorrect input data. Try once more.");
            }
        }
        todo.serializingListOfTasksFromFile();
    }

}
