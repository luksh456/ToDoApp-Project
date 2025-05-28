/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AL FARES
 */
import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private static final String FILE_NAME = "task.txt";

    // Save all tasks to the file
    public static void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                String line = task.getTitle() + "|" + task.getDescription() + "|" + task.getPriority() + "|" + task.isCompleted();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load tasks from the file
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) return tasks; // Return empty if file doesn't exist

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    Task task = new Task(parts[0], parts[1], parts[2]);
                    if (Boolean.parseBoolean(parts[3])) {
                        task.setCompleted(true);
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }
}

