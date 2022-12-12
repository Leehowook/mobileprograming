package com.example.mobileprograming;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mobileprograming.model.ContactItem;
import com.example.mobileprograming.model.TodoItem;

import java.util.ArrayList;

public class PhonebookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        SQLiteDatabase db = openOrCreateDatabase("todo.db", MODE_PRIVATE, null);
        DatabaseService dbService = new DatabaseService(db);

        dbService.createContactTable();

        ArrayList<ContactItem> contactItems = dbService.getContactItemListFromDB();

        ListView contactList = (ListView) findViewById(R.id.activity_phonebook_contactlist_lv);
        ContactListAdapter contactListAdapter = new ContactListAdapter(contactItems, dbService);
        contactList.setAdapter(contactListAdapter);

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

        ConstraintLayout phonebookconstraintLayoutgraybox = findViewById(R.id.activity_phonebook_searchgraybox_cl);
        Button buttonSearch= (Button) findViewById(R.id.activity_phonebook_search_bt);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phonebookconstraintLayoutgraybox.setVisibility(View.VISIBLE);
            }
        });

        Button buttonSearchFavorite= (Button) findViewById(R.id.activity_phonebook_searchgraybox_searchfaborite_bt);
        buttonSearchFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactListAdapter.searchFavorite();
            }
        });
    }
}
