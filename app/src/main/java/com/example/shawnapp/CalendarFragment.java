package com.example.shawnapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shawnapp.Model.Date;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class CalendarFragment extends Fragment implements CalendarAdapter.OnItemListener {

    private RecyclerView calendarRecyclerView;
    private TextView tvDate;
    private LocalDate selectedDate;
    private Database database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarRecyclerView = myView.findViewById(R.id.calendarRecyclerView);
        tvDate = myView.findViewById(R.id.tv_date);
        database = new Database(getActivity());
        selectedDate = LocalDate.now().minusMonths(50001 - getArguments().getInt("date"));
        setMonthView();

        return myView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setMonthView();
    }
    private void setMonthView()
    {
        tvDate.setText(monthYearFromDate(selectedDate));
        ArrayList<Date> daysInMonth = daysInMonthArray(selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this, YearMonth.from(selectedDate));
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<Date> daysInMonthArray(LocalDate date)
    {
        ArrayList<Date> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        double average;
        String dateString;
        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue()-1;
        Log.d("dayOfWeek", String.valueOf(dayOfWeek));
        Log.d("daysInMonth", String.valueOf(daysInMonth));
        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add(new Date());
            }
            else
            {
                dateString = (i - dayOfWeek) +" "+ monthYearFromDate(selectedDate);
                Log.d("check_date", dateString);
                average = database.showAverageInCalendar(dateString);
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

    @Override
    public void onItemClick(int position, String dayText)
    {
        if(!dayText.equals(""))
        {
            String message = dayText + " " + monthYearFromDate(selectedDate);
            Intent intent = new Intent(getActivity(), EvaluateActivity.class);
            intent.putExtra("date", message);
            startActivity(intent);
        }
    }
}