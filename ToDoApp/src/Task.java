



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
/**
 *
 * @author AL FARES
 */
   
public class Task {
    private String title, description, priority;
    private boolean isCompleted;

    public Task(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.isCompleted = false;
    }

    public String getTitle() {
        return title; 
    }
    public String getDescription() { 
        return description; 
    }
    public String getPriority() {
        return priority; 
    }
    public boolean isCompleted() {
        return isCompleted; 
    }

    public void setCompleted(boolean completed) { 
        isCompleted = completed; 
    }

    @Override
    public String toString() {
        return "[" + priority + "] " + title + (isCompleted ? " (Done)" : "");
    }
}
