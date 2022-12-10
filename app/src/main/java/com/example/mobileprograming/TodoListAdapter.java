package com.example.mobileprograming;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileprograming.model.TodoItem;

import java.util.List;

public class TodoListAdapter extends BaseAdapter {

    private final List dataList;


    DatabaseService dbService;

    public TodoListAdapter(List dataList, DatabaseService dbService) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        }
        TodoItem todoItem = (TodoItem) dataList.get(position);

        CheckBox todoCheckBox = convertView.findViewById(R.id.todo_item_cb);
        TextView todoTitle = convertView.findViewById(R.id.todo_item_title_tv);
        TextView todoContent = convertView.findViewById(R.id.todo_item_contents_tv);

        todoCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dbService.deleteToDoItem(todoItem.getId());
                dataList.remove(position);
                notifyDataSetChanged();
                Toast toast = Toast.makeText(parent.getContext(), todoItem.getTitle() +"일정을 완료하였습니다.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), TodoUpdateActivity.class);
                intent.putExtra("id", todoItem.getId());
                intent.putExtra("title", todoItem.getTitle());
                intent.putExtra("content", todoItem.getContent());
                intent.putExtra("date", todoItem.getDate());
                parent.getContext().startActivity(intent);
            }
        });

        todoCheckBox.setChecked(todoItem.getIsDone());
        todoTitle.setText(todoItem.getTitle());
        todoContent.setText(todoItem.getContent());
        return convertView;
    }
}
