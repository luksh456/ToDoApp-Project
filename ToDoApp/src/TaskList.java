/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AL FARES
 */
import java.util.ArrayList;

public class TaskList{
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) { 
        tasks.add(task);
    }
    public void deleteTask(Task task) { 
        tasks.remove(task);
    }
    public ArrayList<Task> getTasks() { 
        return tasks;
    }
}
