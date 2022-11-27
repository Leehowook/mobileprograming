package com.example.mobileprograming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttongophonebook= (Button) findViewById(R.id.activity_main_go_phonebook_bt);
        buttongophonebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),PhonebookActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        Button buttonmainadd= (Button) findViewById(R.id.activity_main_add_bt);
        buttonmainadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),TodocreateActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

    }
}