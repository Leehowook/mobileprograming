package com.example.mobileprograming;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TodoUpdateActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todoread);

        SQLiteDatabase db = openOrCreateDatabase("todo.db", MODE_PRIVATE, null);
        DatabaseService dbService = new DatabaseService(db);

        int id = getIntent().getIntExtra("id", 0);
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String dateStr = getIntent().getStringExtra("date");
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        long date = Long.parseLong(dateStr.substring(0, dateStr.indexOf(" ")));
        Date time = dateFormat.parse(dateStr, new ParsePosition(dateStr.indexOf(" ") + 1));

        EditText titleEditText = (EditText) findViewById(R.id.activity_todoread_todotitle_et);
        EditText contentEditText = (EditText) findViewById(R.id.activity_todoread_todocontents_et);
        CalendarView dateCalendarView = (CalendarView) findViewById(R.id.activity_todoread_cv);
        TimePicker timePicker = (TimePicker) findViewById(R.id.activity_todoread_tp);

        dateCalendarView.setDate(date);
        timePicker.setHour(time.getHours());
        timePicker.setMinute(time.getMinutes());
        dateCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                view.setDate(calendar.getTimeInMillis());
            }
        });


        titleEditText.setText(title);
        contentEditText.setText(content);

        Button buttonUpdate= (Button) findViewById(R.id.activity_todoread_update_bt);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbService.updateToDoItem(
                        id,
                        titleEditText.getText().toString(),
                        contentEditText.getText().toString(),
                        dateCalendarView.getDate() + " " + timePicker.getHour() + ":" + timePicker.getMinute()
                );
                Intent myIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(myIntent);
                finish();
                Toast toast = Toast.makeText(getApplicationContext(), titleEditText.getText() + " 일정이 수정되었습니다.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        Button buttonDelete= (Button) findViewById(R.id.activity_todoread_delete_bt);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbService.deleteToDoItem(id);
                Intent myIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(myIntent);
                finish();
                Toast toast = Toast.makeText(getApplicationContext(), title + " 일정이 삭제되었습니다.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        Button buttonGoBack= (Button) findViewById(R.id.activity_todoread_back_bt);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}