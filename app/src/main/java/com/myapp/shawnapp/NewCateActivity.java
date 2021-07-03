package com.myapp.shawnapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NewCateActivity extends AppCompatActivity {

    FloatingActionButton fab;
    Toolbar toolbar;
    RecyclerView recyclerViewCate;
    NewCateAdapter newCateAdapter;
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
                final Dialog dialog = new Dialog(NewCateActivity.this);
                dialog.setContentView(R.layout.dialog_new_cate);
                final EditText edtCate = dialog.findViewById(R.id.edt_cate);
                Button btnCancel = dialog.findViewById(R.id.btn_cancel);
                Button btnSave = dialog.findViewById(R.id.btn_save);
                dialog.show();
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String cateName = edtCate.getText().toString();
                        if(cateName.equals("")){
                            Toast.makeText(NewCateActivity.this, "Please insert category", Toast.LENGTH_SHORT).show();
                        } else{
                            saveCate(cateName);
                            dialog.dismiss();
                            getCate();
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
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


        newCateAdapter = new NewCateAdapter(NewCateActivity.this, list_cate);
        recyclerViewCate.setAdapter(newCateAdapter);
        recyclerViewCate.setLayoutManager(new LinearLayoutManager(this));
        getCate();
    }

    private void saveCate(String category) {
        Database database = new Database(NewCateActivity.this);
        long result = database.addNewCategory(category);
        if(result == -1){
            Toast.makeText(NewCateActivity.this, "Error or category already exist", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(NewCateActivity.this, "Save successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCate() {
        Cursor cursor = database.showCate();
        list_cate.clear();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                list_cate.add(cursor.getString(0));
            }
        }
        newCateAdapter.notifyDataSetChanged();
    }

    public void dialogEditCate(final String cate_name){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_cate);
        final EditText edtCate = dialog.findViewById(R.id.edt_cate);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        Button btnDelete = dialog.findViewById(R.id.btn_delete);
        Button btnSave = dialog.findViewById(R.id.btn_save);

        edtCate.setText(cate_name);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.deleteCategory(cate_name);
                dialog.dismiss();
                getCate();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newCate = edtCate.getText().toString();
                updateCate(cate_name, newCate);
                dialog.dismiss();
                getCate();
            }
        });
        dialog.show();
    }

    private void updateCate(String oldCategory, String newCategory) {
        Database database = new Database(NewCateActivity.this);
        try {
            long result = database.updateCategory(oldCategory, newCategory);
            if(result != -1){
                Toast.makeText(NewCateActivity.this, "Save successfully!", Toast.LENGTH_SHORT).show();

            }
        }
        catch (Exception e){
            Toast.makeText(NewCateActivity.this, "Error or category already exist", Toast.LENGTH_SHORT).show();
        }
    }
}