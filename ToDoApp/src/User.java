/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author AL FARES
 */


public class User {
    private String username;
    private TaskList taskList;

    public User(String username) {
        this.username = username;
        this.taskList = new TaskList();
    }

    public String getUsername() { 
        return username; 
    }
    public TaskList getTaskList() {
        return taskList; 
    }
}
