package com.example.mobileprograming;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TodocreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todocreate);

        SQLiteDatabase db = openOrCreateDatabase("todo.db", MODE_PRIVATE, null);
        DatabaseService dbService = new DatabaseService(db);


        EditText title = (EditText) findViewById(R.id.activity_todocreate_todotitle_et);
        EditText content = (EditText) findViewById(R.id.activity_todocreate_todocontents_et);
        CalendarView calendarView = (CalendarView) findViewById(R.id.activity_todocreate_cv);
        TimePicker time = (TimePicker) findViewById(R.id.activity_todocreate_tp);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                view.setDate(calendar.getTimeInMillis());
            }
        });

        Calendar calendar = Calendar.getInstance();


        Button buttonCreate= (Button) findViewById(R.id.activity_todocreate_save_bt);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                dbService.insertToDoItem(
                        title.getText().toString(),
                        content.getText().toString(),
                        calendarView.getDate()+" "+time.getHour()+":"+time.getMinute()
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
