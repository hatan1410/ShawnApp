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
import java.util.Random;

public class EvaluateActivity extends AppCompatActivity {

    private EditText edtNotes;
    private Toolbar toolbar;
    private final ArrayList<Category> categories = new ArrayList<>();
    private String date;
    private double sum = 0;
    private TextView tvProgress, tvDate, tvQuote, tvAuthor;
    private ProgressBar progressBar;
    RecyclerView recyclerView;
    private Database database;
    private boolean isChanged = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);

        initView();
        randomQuote();

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
        EvaluateDataAdapter dataAdapter = new EvaluateDataAdapter(this, categories);
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {
        tvDate = findViewById(R.id.tv_date);
        edtNotes = findViewById(R.id.edt_notes);
        recyclerView = findViewById(R.id.recycer_view);
        toolbar = findViewById(R.id.toolBar);
        tvProgress = findViewById(R.id.tv_progress);
        progressBar = findViewById(R.id.progressBar);
        tvQuote = findViewById(R.id.tv_quote);
        tvAuthor = findViewById(R.id.tv_author);
    }

    private void randomQuote() {
        ArrayList<Quote> quotesList = Quote.getListQuote();
        Random random = new Random();
        int randomNumber = random.nextInt(quotesList.size());
        Quote quote = quotesList.get(randomNumber);
        tvQuote.setText(quote.getQuote());
        tvAuthor.setText(quote.getAuthor());
    }

    private void xuLyToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(progressBar.getProgress() != 0){
                    saveData();
                }
                onBackPressed();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void getCate() {
        Cursor cursor = database.showCate();
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
            progressBar.setProgress((int)cursor2.getDouble(0));
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

    public double getSum() {
        return sum;
    }
    public TextView getTvProgress() {
        return tvProgress;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }
}