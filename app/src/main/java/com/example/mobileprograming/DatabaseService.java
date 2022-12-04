package com.example.mobileprograming;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseService {

    private SQLiteDatabase database;

    public DatabaseService(SQLiteDatabase database) {
        this.database = database;
    }

    /** 2개의 테이블 생성
     *  1. todo 테이블에는 todo_id, todo_title, todo_content, todo_date, todo_is_finished
     *  2. contact 테이블에는 contact_id, contact_name, contact_phone, contact_is_favorite
     */

    public void createToDoTable(){
        if(database!= null) {
            String sql = "create table if not exists todo(" +
                    "todo_id integer PRIMARY KEY autoincrement," +
                    "todo_title text," +
                    "todo_content text," +
                    "todo_date text," +
                    "todo_is_finished integer)";
            database.execSQL(sql);
        }else {
            Log.d("DB", "DB is null");
        }
    }

    public void createContactTable(){
        if(database!= null) {
            String sql = "create table if not exists contact(" +
                    "contact_id integer PRIMARY KEY autoincrement," +
                    "contact_name text," +
                    "contact_mobile text," +
                    "contact_is_favorite integer)";
            database.execSQL(sql);
        }else {
            Log.d("DB", "DB is null");
        }
    }

    public void insertToDo(String title, String content, String date, int isFinished){
        if(database!= null) {
            String sql = "insert into todo(todo_title, todo_content, todo_date, todo_is_finished) values(?, ?, ?, ?)";
            Object[] params = {title, content, date, isFinished};
            database.execSQL(sql, params);
        }else {
            Log.d("DB", "DB is null");
        }
    }

    public void insertContact(String name, String mobile, int isFavorite){
        if(database!= null) {
            String sql = "insert into contact(contact_name, contact_mobile, contact_is_favorite) values(?, ?, ?)";
            Object[] params = {name, mobile, isFavorite};
            database.execSQL(sql, params);
        }else {
            Log.d("DB", "DB is null");
        }
    }

    public void selectToDo(){
        if(database!= null) {
            String sql = "select todo_id, todo_title, todo_content, todo_date, todo_is_finished from todo";
            Cursor cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String date = cursor.getString(3);
                int isFinished = cursor.getInt(4);
                Log.d("DB", "id : " + id + ", title : " + title + ", content : " + content + ", date : " + date + ", isFinished : " + isFinished);
            }
        }else {
            Log.d("DB", "DB is null");
        }
    }

    public void selectContact() {
        if (database != null) {
            String sql = "select contact_id, contact_name, contact_mobile, contact_is_favorite from contact";
            Cursor cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                int isFavorite = cursor.getInt(3);
                Log.d("DB", "id : " + id + ", name : " + name + ", mobile : " + phone + ", isFavorite : " + isFavorite);
            }
        } else {
            Log.d("DB", "DB is null");
        }
    }
}
