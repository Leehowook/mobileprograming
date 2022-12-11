package com.example.mobileprograming;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ContactinfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        SQLiteDatabase db = openOrCreateDatabase("todo.db", MODE_PRIVATE, null);
        DatabaseService dbService = new DatabaseService(db);

        int id = getIntent().getIntExtra("id",0);
        String name = getIntent().getStringExtra("name");
        String mobile = getIntent().getStringExtra("mobile");
        boolean isFavorite = getIntent().getIntExtra("isFavorite",0)==1;

        EditText nameEditText = (EditText) findViewById(R.id.activity_contact_info_name_et);
        EditText mobileEditText = (EditText) findViewById(R.id.activity_contact_info_phone_number_et);

        nameEditText.setText(name);
        mobileEditText.setText(mobile);

        Button buttonUpdate= (Button) findViewById(R.id.activity_contact_info_update_bt);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbService.updateContactItem(
                        id,
                        nameEditText.getText().toString(),
                        mobileEditText.getText().toString(),
                        isFavorite
                );
                Intent myIntent = new Intent(getApplicationContext(),PhonebookActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        Button buttonDelete= (Button) findViewById(R.id.activity_contact_info_delete_bt);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbService.deleteContactItem(id);
                Intent myIntent = new Intent(getApplicationContext(),PhonebookActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        Button buttonShare = (Button) findViewById(R.id.activity_contact_info_share_bt);
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, String.format(Locale.getDefault(), "Name: %s, Mobile: %s", name, mobile));
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });

        Button buttonGoBack= (Button) findViewById(R.id.activity_contact_info_back_bt);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),PhonebookActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}
