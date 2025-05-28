/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AL FARES
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToDoGUI extends JFrame {
    private User user;
    private DefaultListModel<Task> taskModel = new DefaultListModel<>();
    private JList<Task> taskJList = new JList<>(taskModel);

    public ToDoGUI(User user) {
        this.user = user;

        setTitle("Smart To-Do List - " + user.getUsername());
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set background for window
        getContentPane().setBackground(new Color(245, 245, 245));

        // JList style
        taskJList.setBackground(new Color(173, 216, 230));
        taskJList.setSelectionBackground(new Color(144, 238, 144));
        taskJList.setSelectionForeground(Color.black);

        // Load tasks from user
        ArrayList<Task> savedTasks = user.getTaskList().getTasks();
        savedTasks.forEach(taskModel::addElement);

        JScrollPane scrollPane = new JScrollPane(taskJList);


        // Buttons
        JButton addBtn = new JButton("Add");
        JButton delBtn = new JButton("Delete");
        JButton doneBtn = new JButton("Mark Complete");
        JButton prioritizeBtn = new JButton("Prioritize");

        // Button colors
        addBtn.setBackground(new Color(76, 175, 80));       // Green
        addBtn.setForeground(Color.WHITE);
        delBtn.setBackground(new Color(244, 67, 54));       // Red
        delBtn.setForeground(Color.WHITE);
        doneBtn.setBackground(new Color(33, 150, 243));     // Blue
        doneBtn.setForeground(Color.WHITE);
        prioritizeBtn.setBackground(new Color(255, 152, 0));// Orange
        prioritizeBtn.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245,245,245));
        buttonPanel.add(addBtn);
        buttonPanel.add(delBtn);
        buttonPanel.add(doneBtn);
        buttonPanel.add(prioritizeBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);

        // Add Task
        addBtn.addActionListener(e -> showAddDialog());

        // Delete Task
        delBtn.addActionListener(e -> {
            Task selected = taskJList.getSelectedValue();
            if (selected != null) {
                user.getTaskList().deleteTask(selected);
                taskModel.removeElement(selected);
                FileHandler.saveTasks(user.getTaskList().getTasks());
            }
        });

        // Mark Complete
        doneBtn.addActionListener(e -> {
            Task selected = taskJList.getSelectedValue();
            if (selected != null) {
                selected.setCompleted(true);
                taskJList.repaint();
                FileHandler.saveTasks(user.getTaskList().getTasks());
            }
        });

        // Prioritize
        prioritizeBtn.addActionListener(e -> {
            ArrayList<Task> sortedTasks = new ArrayList<>(user.getTaskList().getTasks());
            sortedTasks.sort((t1, t2) -> getPriorityValue(t1.getPriority()) - getPriorityValue(t2.getPriority()));
            taskModel.clear();
            for (Task task : sortedTasks) {
                taskModel.addElement(task);
            }
        });

        setVisible(true);
    }

    private void showAddDialog() {
        JTextField titleField = new JTextField();
        JTextField descField = new JTextField();
        JComboBox<String> priorityBox = new JComboBox<>(new String[]{"High", "Medium", "Low"});

        JPanel inputPanel = new JPanel(new GridLayout(0, 1));
        inputPanel.setBackground(new Color(245, 245, 245));
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descField);
        inputPanel.add(new JLabel("Priority:"));
        inputPanel.add(priorityBox);

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Add Task",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText().trim();
            String desc = descField.getText().trim();
            String priority = (String) priorityBox.getSelectedItem();

            if (title.isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in both Title and Description!",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Task newTask = new Task(title, desc, priority);
            user.getTaskList().addTask(newTask);
            taskModel.addElement(newTask);
            FileHandler.saveTasks(user.getTaskList().getTasks());
        }
    }

    private int getPriorityValue(String priority) {
        switch (priority) {
            case "High": return 1;
            case "Medium": return 2;
            case "Low": return 3;
            default: return 4;
        }
    }
}
