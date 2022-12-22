package com.example.mobileprograming;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mobileprograming.model.ContactItem;
import com.example.mobileprograming.model.TodoItem;

import java.util.ArrayList;
import java.util.Collection;

public class DatabaseService {

    private SQLiteDatabase database;

    public DatabaseService(SQLiteDatabase database) {
        this.database = database;
    }

    /** 2개의 테이블 생성
     *  1. todo 테이블에는 todo_id, todo_title, todo_content, todo_date, todo_is_done
     *  2. contact 테이블에는 contact_id, contact_name, contact_phone, contact_is_favorite
     */

    public void createToDoTable(){
        if(database!= null) {
            String sql = "create table if not exists todo(" +
                    "todo_id integer PRIMARY KEY autoincrement," +
                    "todo_title text," +
                    "todo_content text," +
                    "todo_date text," +
                    "todo_is_done integer)";
            database.execSQL(sql);
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
        }
    }

    public void insertToDoItem(String title, String content, String date){
        int isDone = 0;
        if(database!= null) {
            String sql = "insert into todo(todo_title, todo_content, todo_date, todo_is_done) values(?, ?, ?, ?)";
            Object[] params = {title, content, date, isDone};
            database.execSQL(sql, params);
        }
    }

    public void insertContactItem(String name, String mobile, int isFavorite){
        if(database!= null) {
            String sql = "insert into contact(contact_name, contact_mobile, contact_is_favorite) values(?, ?, ?)";
            Object[] params = {name, mobile, isFavorite};
            database.execSQL(sql, params);
        }
    }

    public void selectToDo(){
        if(database!= null) {
            String sql = "select todo_id, todo_title, todo_content, todo_date, todo_is_done from todo";
            Cursor cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String date = cursor.getString(3);
                int isDone = cursor.getInt(4);
                Log.d("DB", "id : " + id + ", title : " + title + ", content : " + content + ", date : " + date + ", isDone : " + isDone);
            }
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

    public ArrayList<TodoItem> getTodoItemListFromDB(){
        ArrayList<TodoItem> todoItemList = new ArrayList<>();
        if(database!= null) {
            String sql = "select todo_id, todo_title, todo_content, todo_date, todo_is_done from todo";
            Cursor cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String date = cursor.getString(3);
                boolean isDone = cursor.getInt(4) == 1;
                TodoItem todoItem = new TodoItem(id, title, content, date, isDone);
                todoItemList.add(todoItem);
            }
        }
        return todoItemList;
    }

    public ArrayList<ContactItem> getContactItemListFromDB(){
        ArrayList<ContactItem> contactItemList = new ArrayList<>();
        if(database!= null) {
            String sql = "select contact_id, contact_name, contact_mobile, contact_is_favorite from contact";
            Cursor cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                boolean isFavorite = cursor.getInt(3) == 1;
                ContactItem contactItem = new ContactItem(id, name, phone, isFavorite);
                contactItemList.add(contactItem);
            }
        }
        return contactItemList;
    }

    public void clearToDoTable(){
        if(database!= null) {
            String sql = "delete from todo";
            database.execSQL(sql);
        }
    }

    public void clearContactTable(){
        if(database!= null) {
            String sql = "delete from contact";
            database.execSQL(sql);
        }
    }
    public void deleteToDoItem(int id){
        if(database!= null) {
            String sql = "delete from todo where todo_id = ?";
            Object[] params = {id};
            database.execSQL(sql, params);
        }
    }

    public void deleteContactItem(int id){
        if(database!= null) {
            String sql = "delete from contact where contact_id = ?";
            Object[] params = {id};
            database.execSQL(sql, params);
        }
    }

    public void updateToDoItem(int id, String title, String content, String date){
        if(database!= null) {
            String sql = "update todo set todo_title = ?, todo_content = ?, todo_date = ?, todo_is_done = ? where todo_id = ?";
            Object[] params = {title, content, date, 0, id};
            database.execSQL(sql, params);
        }
    }

    public void updateContactItem(int id, String name, String mobile, boolean isFavorite){
        if(database!= null) {
            String sql = "update contact set contact_name = ?, contact_mobile = ?, contact_is_favorite = ? where contact_id = ?";
            Object[] params = {name, mobile, isFavorite, id};
            database.execSQL(sql, params);
        }
    }

    public ArrayList<TodoItem> searchTodoTitle(String keyword) {
        ArrayList<TodoItem> todoItemList = new ArrayList<>();
        if(database!= null) {
            String sql = "select todo_id, todo_title, todo_content, todo_date, todo_is_done from todo where todo_title like ?";
            String[] params = {"%" + keyword + "%"};
            Cursor cursor = database.rawQuery(sql, params);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String date = cursor.getString(3);
                boolean isDone = cursor.getInt(4) == 1;
                TodoItem todoItem = new TodoItem(id, title, content, date, isDone);
                todoItemList.add(todoItem);
            }
        }
        return todoItemList;
    }

    public ArrayList<ContactItem> searchContactName(String keyword) {
        ArrayList<ContactItem> contactItemList = new ArrayList<>();
        if(database!= null) {
            String sql = "select contact_id, contact_name, contact_mobile, contact_is_favorite from contact where contact_name like ?";
            String[] params = {"%" + keyword + "%"};
            Cursor cursor = database.rawQuery(sql, params);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                boolean isFavorite = cursor.getInt(3) == 1;
                ContactItem contactItem = new ContactItem(id, name, phone, isFavorite);
                contactItemList.add(contactItem);
            }
        }
        return contactItemList;
    }

    public ArrayList<ContactItem> searchFavorite() {
        ArrayList<ContactItem> contactItemList = new ArrayList<>();
        if(database!= null) {
            String sql = "select contact_id, contact_name, contact_mobile, contact_is_favorite from contact where contact_is_favorite = ?";
            String[] params = {"1"};
            Cursor cursor = database.rawQuery(sql, params);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                boolean isFavorite = cursor.getInt(3) == 1;
                ContactItem contactItem = new ContactItem(id, name, phone, isFavorite);
                contactItemList.add(contactItem);
            }
        }
        return contactItemList;
    }
}
