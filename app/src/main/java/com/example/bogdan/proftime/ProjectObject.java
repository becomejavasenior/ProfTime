package com.example.bogdan.proftime;

import java.util.List;

/**
 * Created by root on 13.12.15.
 */
public class ProjectObject {

    private int id;
    private String status;
    private List<Task> tasks;

    public ProjectObject() {
    }

    public ProjectObject(int id, String status, List<Task> tasks) {
        this.id = id;
        this.status = status;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}