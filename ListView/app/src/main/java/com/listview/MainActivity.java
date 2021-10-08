package com.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private  DBManager dbManager;
    Adapter adapter;
    Button btn;
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);
        dbManager.open();
        ArrayList<account> list = dbManager.read();
        recyclerView = findViewById(R.id.rcview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter( list);
        recyclerView.setAdapter(adapter);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text=findViewById(R.id.editTextTextPersonName);
                String name = text.getText().toString();
                list.add(new account(name));
                adapter = new Adapter( list);
                recyclerView.setAdapter(adapter);
                dbManager.insert(new account(name));
                text.setText("");
            }
        });
    }
}