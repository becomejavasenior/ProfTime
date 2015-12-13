package com.example.bogdan.proftime;

import java.util.Date;

/**
 * Created by root on 13.12.15.
 */
public class Task {

    private int id;
    private String title;
    private String status;
    private String url;
    private int amount;
    private String create;

    public Task() {
    }

    public Task(int id, String title, String status, String url, int amount, String create) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.url = url;
        this.amount = amount;
        this.create = create;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }
}
