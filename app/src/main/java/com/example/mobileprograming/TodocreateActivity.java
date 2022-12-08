package com.example.mobileprograming;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TodocreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todocreate);

        SQLiteDatabase db = openOrCreateDatabase("todo.db", MODE_PRIVATE, null);
        DatabaseService dbService = new DatabaseService(db);


        EditText title = (EditText) findViewById(R.id.activity_todocreate_todotitle_et);
        EditText content = (EditText) findViewById(R.id.activity_todocreate_todocontents_et);
        Button buttonCreate= (Button) findViewById(R.id.activity_todocreate_save_bt);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbService.createToDoTable();
                dbService.insertToDoItem(
                        title.getText().toString(),
                        content.getText().toString(),
                        "test"
                );

                Intent myIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        Button buttongoback= (Button) findViewById(R.id.activity_todocreate_back_bt);
        buttongoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}
