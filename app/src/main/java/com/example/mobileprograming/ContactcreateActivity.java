package com.example.mobileprograming;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ContactcreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_create);

        SQLiteDatabase db = openOrCreateDatabase("todo.db", MODE_PRIVATE, null);
        DatabaseService dbService = new DatabaseService(db);

        EditText name = (EditText) findViewById(R.id.activity_contact_create_name_et);
        EditText mobile = (EditText) findViewById(R.id.activity_contact_create_phonenumber_et);

        Button buttonCreate= (Button) findViewById(R.id.activity_contact_create_save_bt);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbService.createContactTable();
                dbService.insertContactItem(
                        name.getText().toString(),
                        mobile.getText().toString(),
                        0
                );

                Intent myIntent = new Intent(getApplicationContext(),PhonebookActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        Button buttongoback= (Button) findViewById(R.id.activity_contact_create_back_bt);
        buttongoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),PhonebookActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}
