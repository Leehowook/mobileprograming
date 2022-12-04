package com.example.mobileprograming;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deleteDatabase("app.db");

        SQLiteDatabase database = openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        DatabaseService databaseService = new DatabaseService(database);
        databaseService.createToDoTable();
        databaseService.createContactTable();
        databaseService.insertToDo("마트가기", "사과,당근,딸기", "2022-12-12", 0);
        databaseService.insertToDo("책사기", "자바,안드로이드", "2022-12-13", 0);
        databaseService.insertContact("조현석", "010-1234-5678", 0);
        databaseService.insertContact("이호욱", "010-1234-5678", 0);
        databaseService.selectToDo();
        databaseService.selectContact();
    }
}
