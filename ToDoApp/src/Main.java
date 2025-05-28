/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AL FARES
 */
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create a user
        User user = new User("LPSN");

        // Load tasks from task.txt
        ArrayList<Task> loadedTasks = FileHandler.loadTasks();
        for (Task task : loadedTasks) {
            user.getTaskList().addTask(task);
        }

        // Launch GUI
        javax.swing.SwingUtilities.invokeLater(() -> new ToDoGUI(user));
    }
}

