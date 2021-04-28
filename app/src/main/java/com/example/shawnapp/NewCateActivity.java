package com.example.shawnapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NewCateActivity extends AppCompatActivity {

    FloatingActionButton fab;
    Toolbar toolbar;
    RecyclerView recyclerViewCate;
    ArrayList<String> list_cate = new ArrayList<>();
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cate);

        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolBar);
        recyclerViewCate = findViewById(R.id.recyclerview_cate);
        database = new Database(NewCateActivity.this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewCateActivity.this, "fab", Toast.LENGTH_SHORT).show();
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Category");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getCate();
        NewCateAdapter newCateAdapter = new NewCateAdapter(NewCateActivity.this, list_cate);
        recyclerViewCate.setAdapter(newCateAdapter);
        recyclerViewCate.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getCate() {
        Cursor cursor = database.showCate();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                list_cate.add(cursor.getString(0));
            }
        }
    }
}