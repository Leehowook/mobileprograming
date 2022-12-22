package com.example.mobileprograming;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static final String Tag = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLiteDatabase db = openOrCreateDatabase("todo.db", MODE_PRIVATE, null);
        DatabaseService dbService = new DatabaseService(db);


        dbService.createToDoTable();

        // 샘플 일정 추가
        dbService.insertToDoItem("마트 가기", "사과, 당근", "1600000003330 12:00");
        dbService.insertToDoItem("운동하기", "달리기, 헬스", "1600003333333 12:00");
        dbService.insertToDoItem("코딩하기", "안드로이드, 자바", "1600011111000 12:00");

        dbService.createContactTable();

        // 샘플 연락처 추가
        dbService.insertContactItem("조셉", "010-0000-0000", 0);
        dbService.insertContactItem("필라테스", "010-3002-4300", 1);
        dbService.insertContactItem("조현석", "010-1111-1111", 1);
        dbService.insertContactItem("이호욱", "010-2222-2222", 0);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
