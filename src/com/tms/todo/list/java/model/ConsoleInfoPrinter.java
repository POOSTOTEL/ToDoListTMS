package src.com.tms.todo.list.java.model;

public class ConsoleInfoPrinter {
    public static void printMenu () {
        System.out.println("Welcome to ToDoList from a TMS student Alex.");
        System.out.println("Enter option number:");
        System.out.println("1) Create a new task;");
        System.out.println("2) Edit an existing task;");
        System.out.println("3) Cancel an existing task;");
        System.out.println("4) Mark a task as finished;");
        System.out.println("5) Delete an existing task;");
        System.out.println("6) Show all active tasks;");
        System.out.println("7) Show description of existing task;");
        System.out.println("8) Filter tasks by status and display;");
        System.out.println("9) Filter tasks by their finish time;");
        System.out.println("10) Find a task by name;");
        System.out.println("11) Show statistics;");
        System.out.println("0) Exit the program.");
    }
    public static void printTypeOfAddedTask () {
        System.out.println("Does the task have a deadline?");
        System.out.println("1) Yes;");
        System.out.println("2) No.");
    }
    public static void printNameInputInformation () {
        System.out.println("Enter the name of the task and press enter.");
        System.out.println("(Length no more than 25 characters!)");

    }
    public static void printDescriptionInputInformation () {
        System.out.println("Enter the description of the task and press enter.");
        System.out.println("(Length no more than 100 characters!)");
    }
    public static void printDateInputInformation () {
        System.out.println("Enter date and time and press enter.");
        System.out.println("(Input format \"yyyy-MM-dd HH:mm\")");
    }
}
