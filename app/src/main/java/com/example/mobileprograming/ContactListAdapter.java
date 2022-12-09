package com.example.mobileprograming;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.mobileprograming.model.ContactItem;
import com.example.mobileprograming.model.TodoItem;

import java.util.List;

public class ContactListAdapter extends BaseAdapter {

    private final List dataList;


    DatabaseService dbService;

    public ContactListAdapter(List dataList, DatabaseService dbService) {
        this.dataList = dataList;
        this.dbService = dbService;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        }

        ContactItem contactItem = (ContactItem) dataList.get(position);

        TextView contactName = convertView.findViewById(R.id.contact_item_name_tv);
        TextView contactNumber = convertView.findViewById(R.id.contact_item_phone_number_tv);
        TextView isFavorite = convertView.findViewById(R.id.contact_item_bookmark_tv);

        contactName.setText(contactItem.getName());
        contactNumber.setText(contactItem.getMobile());
        isFavorite.setText(contactItem.getIsFavorite() ? "★" : "☆");

        isFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return convertView;
    }
}
