package com.example.shawnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shawnapp.Model.Category;

import java.util.ArrayList;

public class EvaluateActivity extends AppCompatActivity {

    private EditText edtNotes;
    private Toolbar toolbar;
    private final ArrayList<Category> categories = new ArrayList<>();
    private String date;
    public double sum = 0;
    public TextView tvProgress;
    public ProgressBar progressBar;
    private Database database;
    private boolean isSaved = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);

        TextView tvDate = findViewById(R.id.tv_date);
        edtNotes = findViewById(R.id.edt_notes);
        RecyclerView recyclerView = findViewById(R.id.recycer_view);
        toolbar = findViewById(R.id.toolBar);
        tvProgress = findViewById(R.id.tv_progress);
        progressBar = findViewById(R.id.progressBar);

        database = new Database(EvaluateActivity.this);
        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        tvDate.setText(date);
        if(database.isNewDate(date)){
            getCate();
        }
        else {
            getDateInDB();
        }
        xuLyToolBar();

        //xu ly list categories trong recycle view
        DataAdapter dataAdapter = new DataAdapter(this, categories);
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void xuLyToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(date);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSaved || tvProgress.getText().toString().equals("0%")){
                    onBackPressed();
                }
                else if(!isSaved) {
                   // DialogBack();
                    final Dialog dialog = new Dialog(EvaluateActivity.this);
                    dialog.setContentView(R.layout.dialog_save);
                    Button btnCancel = dialog.findViewById(R.id.btn_cancel);
                    Button btnDiscard = dialog.findViewById(R.id.btn_discard);
                    Button btnSave = dialog.findViewById(R.id.btn_save);
                    dialog.show();
                    btnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            isSaved = true;
                            saveData();
                            Log.d("check", "save");
                            onBackPressed();
                        }
                    });
                    btnDiscard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("check", "Discard");
                            onBackPressed();
                        }
                    });
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("check", "Cancel");
                            dialog.cancel();
                        }
                    });
                }
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_evaluate, menu);
        return super.onCreateOptionsMenu(menu);
    }

//Item save tren menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        isSaved = true;
        saveData();
        return super.onOptionsItemSelected(item);
    }

    private void getCate() {
        Cursor cursor = database.showNewDate();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                categories.add(new Category(cursor.getString(0), 0));
            }
        }

    }

    private void getDateInDB() {
        Cursor cursor1 = database.showDataCate(date);
        Cursor cursor2 = database.showAverageAndNote(date);

        if(cursor1.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor1.moveToNext()){
                categories.add(new Category(cursor1.getString(0), cursor1.getInt(1)));
            }
        }
        while (cursor2.moveToNext()){
            tvProgress.setText(String.valueOf(cursor2.getDouble(0)));
            edtNotes.setText(cursor2.getString(1));
        }

    }

    private void saveData(){
        if(database.isNewDate(date)){
            for(int i=0; i<categories.size(); i++){
                database.saveEvaluatePoint(date, categories.get(i).getCateName(), categories.get(i).getPoint());
            }
            database.saveAverageAndNote(date, Double.parseDouble(tvProgress.getText().toString()), edtNotes.getText().toString());
        } else{
            for(int i=0; i<categories.size(); i++){
                database.updateEvaluatePoint(date, categories.get(i).getCateName(), categories.get(i).getPoint());
            }
            database.updateAverageAndNote(date, Double.parseDouble(tvProgress.getText().toString()), edtNotes.getText().toString());
        }
    }
}