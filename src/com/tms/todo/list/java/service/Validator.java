package src.com.tms.todo.list.java.service;

import src.com.tms.todo.list.java.model.Task;

import java.util.ArrayList;

public class Validator {
    public static boolean validateName (String name) {
        if (name.length() < 25) {
            return true;
        } else {
            System.out.println("Name has incorrect form. Try again.");
            return false;
        }
    }
    public static boolean validateDescription (String description) {
        if (description.length() < 100) {
            return true;
        } else {
            System.out.println("Description has incorrect form. Try again.");
            return false;
        }
    }
    public static boolean validateNumber (int number, ArrayList<Task> toDo) {
        if (toDo.stream().noneMatch(task -> task.getNumber() == number)) {
            System.out.println("This number does not exist.");
            return false;
        } else {
            return true;
        }
    }
}
