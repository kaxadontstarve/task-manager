package service;

import model.Task;
import storage.TaskStorage;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;//Список всіх завдань
    private final TaskStorage storage;//Об'єкт збереження та завантаження з файлу
    //Конструктор який при створенні завантажує інформацію з файлу
    public TaskManager() {
        this.storage = new TaskStorage();
        this.tasks = new ArrayList<>(storage.loadTasks());
    }
    //Створює нове завдання
    public void addTask(String description) {
        tasks.add(new Task(description));
        save();
    }
    //Видаляє завдання
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            save();
        }
    }
    //Змінює статус завдань на виконано\не виконано
    public void toggleDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setDone(!task.isDone());
            save();
        }
    }
    //Редагує існуюче завдання
    public void updateTask(int index, String newDescription) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setDescription(newDescription);
            save();
        }
    }
    //Повертає список всіх завдань
    public List<Task> getTasks() {
        return tasks;
    }
    //Зберігає зміни в файл
    private void save() {
        storage.saveTasks(tasks);
    }
}