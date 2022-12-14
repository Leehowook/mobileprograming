package com.example.mobileprograming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mobileprograming.model.TodoItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = openOrCreateDatabase("todo.db", MODE_PRIVATE, null);
        DatabaseService dbService = new DatabaseService(db);

        dbService.createToDoTable();

        ArrayList<TodoItem> todoItems = dbService.getTodoItemListFromDB();

        ListView todolist = (ListView) findViewById(R.id.activity_main_todolist_lv);
        TodoListAdapter todoListAdapter = new TodoListAdapter(todoItems, dbService);
        todolist.setAdapter(todoListAdapter);

        Button buttonGoPhonebook= (Button) findViewById(R.id.activity_main_go_phonebook_bt);
        buttonGoPhonebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),PhonebookActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        Button buttonMainAdd= (Button) findViewById(R.id.activity_main_add_bt);
        buttonMainAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),TodocreateActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        ConstraintLayout mainconstraintLayoutgraybox = findViewById(R.id.activity_main_searchgraybox_cl);
        Button buttonSearch= (Button) findViewById(R.id.activity_main_search_bt);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainconstraintLayoutgraybox.setVisibility(View.VISIBLE);
            }
        });

        EditText editTextsearch = findViewById(R.id.activity_main_searchgraybox_edittext_et);
        Button buttonSendsearchTodoTitle = findViewById(R.id.activity_main_searchgraybox_sendbutton_bt);
        buttonSendsearchTodoTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = editTextsearch.getText().toString();
                todoListAdapter.searchTodoTitle(searchText);
            }
        });
    }
}