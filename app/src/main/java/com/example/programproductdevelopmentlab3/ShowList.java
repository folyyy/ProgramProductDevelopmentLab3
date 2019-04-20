package com.example.programproductdevelopmentlab3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;

import java.util.ArrayList;

public class ShowList extends AppCompatActivity {

    ArrayList<StudentsList> historyData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list);
        StudentDatabase db = new StudentDatabase(this);
        historyData = db.getAllStudents();
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StudentsListAdapter studentsListAdapter = new StudentsListAdapter(historyData);
        recyclerView.setAdapter(studentsListAdapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
