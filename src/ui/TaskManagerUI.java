package ui;

import service.TaskManager;
import model.Task;

import javax.swing.*;
import java.awt.*;

public class TaskManagerUI {
    private final TaskManager taskManager;
    private final DefaultListModel<String> taskListModel;
    private final JList<String> taskList;

    public TaskManagerUI() {
        taskManager = new TaskManager();

        JFrame frame = new JFrame("Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Верх: поле + кнопка добавить
        JTextField inputField = new JTextField();
        JButton addButton = new JButton("Додати");

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        // Центр: список задач
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Низ: кнопки управления
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        JButton deleteButton = new JButton("Видалити");
        JButton toggleDoneButton = new JButton("Завершенно");
        JButton editButton = new JButton("Редагувати");

        bottomPanel.add(deleteButton);
        bottomPanel.add(toggleDoneButton);
        bottomPanel.add(editButton);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

        // Загрузить задачи
        loadTasks();

        // Добавить задачу
        addButton.addActionListener(e -> {
            String text = inputField.getText().trim();
            if (!text.isEmpty()) {
                taskManager.addTask(text);
                refreshList();
                inputField.setText("");
            }
        });

        // Удалить задачу
        deleteButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                taskManager.deleteTask(index);
                refreshList();
            }
        });

        // Переключить выполнено
        toggleDoneButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                taskManager.toggleDone(index);
                refreshList();
            }
        });

        // Редактировать
        editButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                String current = taskManager.getTasks().get(index).getDescription();
                String updated = JOptionPane.showInputDialog(frame, "Редагувати завдання:", current);
                if (updated != null && !updated.trim().isEmpty()) {
                    taskManager.updateTask(index, updated.trim());
                    refreshList();
                }
            }
        });
    }

    private void loadTasks() {
        for (Task task : taskManager.getTasks()) {
            taskListModel.addElement(task.toString());
        }
    }

    private void refreshList() {
        taskListModel.clear();
        loadTasks();
    }
}