package com.example.mobileprograming;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

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
        TextView favoriteButton = convertView.findViewById(R.id.contact_item_bookmark_tv);
        Button callButton = convertView.findViewById(R.id.contact_item_call_btn);
        Button smsButton = convertView.findViewById(R.id.contact_item_message_btn);

        contactName.setText(contactItem.getName());
        contactNumber.setText(contactItem.getMobile());
        favoriteButton.setText(contactItem.getIsFavorite() ? "★" : "☆");

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbService.updateContactItem(contactItem.getId(), contactItem.getName(), contactItem.getMobile(), !contactItem.getIsFavorite());
                String toastMessage = contactItem.getIsFavorite() ? "즐겨찾기에서 삭제되었습니다." : "즐겨찾기에 추가되었습니다.";
                Toast.makeText(parent.getContext(), toastMessage, Toast.LENGTH_SHORT).show();
                dataList.clear();
                dataList.addAll(dbService.getContactItemListFromDB());
                notifyDataSetChanged();
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactItem.getMobile()));
                parent.getContext().startActivity(intent);
            }
        });

        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + contactItem.getMobile()));
                parent.getContext().startActivity(intent);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), ContactinfoActivity.class);
                intent.putExtra("id", contactItem.getId());
                intent.putExtra("name", contactItem.getName());
                intent.putExtra("mobile", contactItem.getMobile());
                intent.putExtra("isFavorite", contactItem.getIsFavorite());
                parent.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
