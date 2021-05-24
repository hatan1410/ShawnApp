package com.example.shawnapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shawnapp.Model.Date;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>
{
    Context context;
    private final ArrayList<Date> daysOfMonth;
    private final OnItemListener onItemListener;
    private final YearMonth yearMonth;

    public CalendarAdapter(Context context, ArrayList<Date> daysOfMonth, OnItemListener onItemListener, YearMonth yearMonth) {
        this.context = context;
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.yearMonth = yearMonth;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if(viewType == 1){
            view = inflater.inflate(R.layout.today_cell, parent, false);
        }
        else if(viewType == 2){
            view = inflater.inflate(R.layout.calendar_not_month_cell, parent, false);
        }
        else {
            view = inflater.inflate(R.layout.calendar_cell, parent, false);
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    //gan gia tri cho text view
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
    {
        Date date = daysOfMonth.get(position);
        double average = date.getAverage();
        holder.tvDayOfMonth.setText(date.getDate());
        if(date.getDate() == null){
            holder.tvAverage.setText("");
        } else {
            if (average == (int) average) {
                holder.tvAverage.setText(String.valueOf((int) average));
            }
            else {
                holder.tvAverage.setText(String.valueOf(average));
            }
        }

        if(average < 20){
            holder.tvAverage.setTextColor(ContextCompat.getColor(context, R.color.red));
        }
        else if(average >= 20 && average < 40){
            holder.tvAverage.setTextColor(ContextCompat.getColor(context, R.color.orange));
        }
        else if(average >= 40 && average < 60){
            holder.tvAverage.setTextColor(ContextCompat.getColor(context, R.color.yellow));
        }
        else if(average >= 60 && average < 80){
            holder.tvAverage.setTextColor(ContextCompat.getColor(context, R.color.green2));
        }
        else{
            holder.tvAverage.setTextColor(ContextCompat.getColor(context, R.color.blue2));
        }
    }

    @Override
    public int getItemViewType(int position){
        if(daysOfMonth.get(position).getYearMonth().equals(monthYearFromDate(YearMonth.from(LocalDate.now()))) && Integer.parseInt(daysOfMonth.get(position).getDate()) == LocalDate.now().getDayOfMonth()){
            return 1;
        }
        if(!daysOfMonth.get(position).getYearMonth().equals(monthYearFromDate(yearMonth))){
            return 2;
        }
        return 0;
    }
    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    public interface  OnItemListener
    {
        void onItemClick(int position, String dayText);
    }

    private String monthYearFromDate(YearMonth month)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return month.format(formatter);
    }
}

