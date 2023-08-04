package src.com.tms.todo.list.java.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ToDo {
    private File todoList = new File(System.getProperty("user.home") + "\\IdeaProjects\\ToDoListTMS\\src\\com\\tms\\todo\\list\\resources", "todoList.txt");
    public ToDo() {
        createFile();
    }
    private void createFile () {
        if (!this.todoList.exists()) {
            try {
                this.todoList.createNewFile();
            } catch (IOException e) {
                System.out.println("We have problems with creating new file :(");
            }
        }
    }
    public void addTask (Task task) {
    }

}
