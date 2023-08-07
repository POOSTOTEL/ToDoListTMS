package src.com.tms.todo.list.java.model;

import src.com.tms.todo.list.java.service.Validator;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class ToDo {
    private File fileWithTaskList = new File(System.getProperty("user.home") + "\\IdeaProjects\\ToDoListTMS\\src\\com\\tms\\todo\\list\\resources", "todoList.txt");
    private ArrayList<Task> arrayList = new ArrayList<>();

    public ToDo() {
        createFile();
    }

    public void createFile() {
        if (!this.fileWithTaskList.exists()) {
            try {
                this.fileWithTaskList.createNewFile();
            } catch (IOException e) {
                System.out.println("We have problems with creating new file :(");
            }
        }
    }

    public void addTask(Task task) {
        arrayList.add(task);
    }

    public void editTaskName(int taskNumber, String newName) {
        if (Validator.validateNumber(taskNumber, arrayList)) {
            arrayList.stream()
                    .filter(task -> task.number == taskNumber)
                    .forEach(task -> task.setName(newName));
        }
    }

    public void editTaskDescription(int taskNumber, String newDescription) {
        if (Validator.validateNumber(taskNumber, arrayList)) {
            arrayList.stream()
                    .filter(task -> task.number == taskNumber)
                    .forEach(task -> task.setDescription(newDescription));
        }
    }

    public void setCanceledStatus(int taskNumber) {
        if (Validator.validateNumber(taskNumber, arrayList)) {
            arrayList.stream()
                    .filter(task -> task.number == taskNumber)
                    .forEach(task -> task.setStatus(Status.CANCELED));
        }
    }

    public void setFinishedStatus(int taskNumber) {
        if (Validator.validateNumber(taskNumber, arrayList)) {
            arrayList.stream()
                    .filter(task -> (task.number == taskNumber
                            && task.getStatus() != Status.CANCELED
                            && task.getStatus() != Status.OVERDUE))
                    .forEach(task -> task.setStatus(Status.CANCELED));
        }
    }

    public void deleteTask(int taskNumber) {
        if (Validator.validateNumber(taskNumber, arrayList)) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).number == taskNumber) {
                    arrayList.remove(i);
                    break;
                }
            }
        }
    }
    public void printShortInfoAboutTasks() {
        arrayList.forEach((task) -> System.out.println(task.shortToString()));
    }
    public void printTask(int taskNumber) {
        if (Validator.validateNumber(taskNumber, arrayList)) {
            if (Validator.validateNumber(taskNumber, arrayList)) {
                arrayList.stream()
                        .filter(task -> (task.number == taskNumber))
                        .forEach(task -> System.out.println(task.toString()));
            }
        }
    }
    public void printFilteredByStatus (Status status) {
        arrayList.stream().filter(task -> task.getStatus() == status).forEach(task -> System.out.println(task.toString()));
    }
    public void printFilteredByFinishTime (LocalDateTime time) {
        arrayList.stream().filter(task -> task.getCompletionTime().isAfter(time)).forEach(task -> System.out.println(task.toString()));
    }
    public void searchTaskByName (String phrase) {
        arrayList.stream().filter(task -> task.getName().contains(phrase)).forEach(task -> System.out.println(task.toString()));
    }
    public void deserializingListOfTasksFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileWithTaskList))) {
            arrayList = (ArrayList<Task>) objectInputStream.readObject();
            Optional<Task> taskWithMaxTaskNumber = arrayList.stream()
                    .max(Comparator.comparingInt(task -> task.number));
            Task.maxTaskNumber = taskWithMaxTaskNumber.get().number;;
            System.out.println(arrayList);
        } catch (FileNotFoundException e) {
            System.out.println("We can't find input file :(");
        } catch (IOException e) {
            System.out.println("We has some problems with file access :(");
        } catch (ClassNotFoundException e) {
            System.out.println("We has problems with deserialization :(");
        }
    }

    public void serializingListOfTasksFromFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileWithTaskList))) {
            objectOutputStream.writeObject(arrayList);
        } catch (FileNotFoundException e) {
            System.out.println("We can't find input file :(");
        } catch (IOException e) {
            System.out.println("We has some problems with file access :(");
        }
    }
}