package com.example.mobileprograming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PhonebookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        Button buttongomain= (Button) findViewById(R.id.activity_phonebook_go_main_bt);
        buttongomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        Button buttongoadd= (Button) findViewById(R.id.activity_phonebook_add_bt);
        buttongoadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),ContactcreateActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}
