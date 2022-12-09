package com.example.mobileprograming;

public class TodoItem {
    int todo_id;
    String todo_title;
    String todo_content;
    String todo_date;
    int todo_is_finished;

    public TodoItem(int todo_id, String todo_title, String todo_content, String todo_date, int todo_is_finished){
        this.todo_id = todo_id;
        this.todo_title = todo_title;
        this.todo_content = todo_content;
        this.todo_date = todo_date;
        this.todo_is_finished = todo_is_finished;
    }

    public String getTodo_title(){
        return todo_title;
    }

    public void setTodo_title(String todo_title){
        this.todo_title = todo_title;
    }

    public String getTodo_content(){
        return todo_content;
    }

    public void setTodo_content(String todo_content){
        this.todo_content = todo_content;
    }

    public String getTodo_date(){
        return todo_date;
    }

    public void setTodo_date(String todo_date){
        this.todo_date = todo_date;
    }

    public int getTodo_is_finished(){
        return todo_is_finished;
    }

    public void setTodo_is_finished(int todo_is_finished){
        this.todo_is_finished = todo_is_finished;
    }

}
