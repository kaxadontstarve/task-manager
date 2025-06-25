package model;

public class Task {
    private String description;//Опис завдання
    private boolean done;//Статус задачі, (виконано\не виконано)
    //Конструктор який створю завдання з описом, за замовчуванням воно не завершене
    public Task(String description) {
        this.description = description;
        this.done = false;
    }
    //Метод повертае опис завдання
    public String getDescription() {
        return description;
    }
    //Метод повертае статус завдання
    public boolean isDone() {
        return done;
    }
    //Метод встановлює опис завдання
    public void setDescription(String description) {
        this.description = description;
    }
    //Метод встановлює статус завдань
    public void setDone(boolean done) {
        this.done = done;
    }
    //Перевстановлює функцію toString для потрібного виводу завдань на екран
    @Override
    public String toString() {
        return (done ? "[✓] " : "[ ] ") + description;
    }
}