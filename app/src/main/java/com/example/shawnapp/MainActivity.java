package com.example.shawnapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shawnapp.Model.Date;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private Toolbar toolbar;
    private GestureDetector gestureDetector;
    private Database database;
    final int SWIPE_THRESHOLD = 100;
    final int SWIPE_VELOCITY_THRESHOLD = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        setSupportActionBar(toolbar);
        selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        toolbar = findViewById(R.id.toolBar_main);
        gestureDetector = new GestureDetector(this, new MyGesture());
        database = new Database(MainActivity.this);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        getSupportActionBar().setTitle(monthYearFromDate(selectedDate));
        ArrayList<Date> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        calendarRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    private ArrayList<Date> daysInMonthArray(LocalDate date)
    {
        ArrayList<Date> daysInMonthArray = new ArrayList<>();
        //ArrayList<Date> dateArrayList = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        double average;
        String dateString;
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue()-1;

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                //daysInMonthArray.add("");
                daysInMonthArray.add(new Date());
            }
            else
            {
                dateString = String.valueOf(i - dayOfWeek) +" "+ monthYearFromDate(selectedDate);
                average = database.showAverageInCalendar(dateString);
                //daysInMonthArray.add(String.valueOf(i - dayOfWeek));
               daysInMonthArray.add(new Date(String.valueOf(i - dayOfWeek), average));
            }
        }
        return  daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    //xu ly su kien
    @Override
    public void onItemClick(int position, String dayText)
    {
        if(!dayText.equals(""))
        {
            String message = dayText + " " + monthYearFromDate(selectedDate);
            Intent intent = new Intent(MainActivity.this, EvaluateActivity.class);
            intent.putExtra("date", message);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    class MyGesture extends GestureDetector.SimpleOnGestureListener{
        protected MotionEvent mLastOnDownEvent = null;
        @Override
        public boolean onDown(MotionEvent e) {
            mLastOnDownEvent = e;
            return true;//super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1==null){
                e1 = mLastOnDownEvent;
            }
            if (e1==null || e2==null){
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                return false;
            }
            //Keo tu trai sang phai
            if(e2.getX() - e1.getX() > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
                selectedDate = selectedDate.minusMonths(1);
                setMonthView();
            }//Keo tu phai sang trai
            if(e1.getX() - e2.getX() > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
                selectedDate = selectedDate.plusMonths(1);
                setMonthView();
            }
            return true;
        }
    }
}