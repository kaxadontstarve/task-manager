package storage;

import model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private static final String FILE_PATH = "tasks.txt";

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 2);
                if (parts.length == 2) {
                    Task task = new Task(parts[1]);
                    task.setDone(Boolean.parseBoolean(parts[0]));
                    tasks.add(task);
                }
            }
        } catch (IOException ignored) {}
        return tasks;
    }

    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.isDone() + ";" + task.getDescription());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}