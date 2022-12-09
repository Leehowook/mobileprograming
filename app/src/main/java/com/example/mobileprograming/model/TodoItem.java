package com.example.mobileprograming.model;

public class TodoItem {
    private int id;
    private String title;
    private String content;
    private String date;
    private boolean isDone;

    public TodoItem(int id, String title, String content, String date, boolean isDone) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
