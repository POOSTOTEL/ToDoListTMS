package src.com.tms.todo.list.java.service;

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
        if (description.length() < 25) {
            return true;
        } else {
            System.out.println("Description has incorrect form. Try again.");
            return false;
        }
    }
}
