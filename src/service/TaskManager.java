package service;

import model.Task;
import storage.TaskStorage;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;
    private final TaskStorage storage;

    public TaskManager() {
        this.storage = new TaskStorage();
        this.tasks = new ArrayList<>(storage.loadTasks());
    }

    public void addTask(String description) {
        tasks.add(new Task(description));
        save();
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            save();
        }
    }

    public void toggleDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setDone(!task.isDone());
            save();
        }
    }

    public void updateTask(int index, String newDescription) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDescription(newDescription);
            save();
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    private void save() {
        storage.saveTasks(tasks);
    }
}