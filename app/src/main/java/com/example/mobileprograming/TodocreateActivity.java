package com.example.mobileprograming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TodocreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todocreate);

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
