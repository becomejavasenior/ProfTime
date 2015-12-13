package com.example.bogdan.proftime;

import java.util.List;

/**
 * Created by root on 13.12.15.
 */
public class ProjectObject {

    private int id;
    private String firstNameCustomer;
    private String lastNameCustomer;
    private String status;
    private List<Task> tasks;

    public ProjectObject() {
    }

    public ProjectObject(int id, String firstNameCustomer, String lastNameCustomer, String status, List<Task> tasks) {
        this.id = id;
        this.firstNameCustomer = firstNameCustomer;
        this.lastNameCustomer = lastNameCustomer;
        this.status = status;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstNameCustomer() {
        return firstNameCustomer;
    }

    public void setFirstNameCustomer(String firstNameCustomer) {
        this.firstNameCustomer = firstNameCustomer;
    }

    public String getLastNameCustomer() {
        return lastNameCustomer;
    }

    public void setLastNameCustomer(String lastNameCustomer) {
        this.lastNameCustomer = lastNameCustomer;
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