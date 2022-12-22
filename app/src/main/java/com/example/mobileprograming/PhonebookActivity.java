package com.example.mobileprograming;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        // 샘플 연락처 추가
        dbService.insertContactItem("조셉", "010-0000-0000", 0);
        dbService.insertContactItem("필라테스", "010-3002-4300", 1);
        dbService.insertContactItem("조현석", "010-1111-1111", 1);
        dbService.insertContactItem("이호욱", "010-2222-2222", 0);


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

        EditText editTextSearchName= (EditText) findViewById(R.id.activity_phonebook_searchgraybox_edittext_et);
        Button buttonSendSeachingName= (Button) findViewById(R.id.activity_phonebook_searchgraybox_sendbutton_bt);
        buttonSendSeachingName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextSearchName.getText().toString();
                contactListAdapter.searchContactName(name);
            }
        });
    }
}
