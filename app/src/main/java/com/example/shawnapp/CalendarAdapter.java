package com.example.shawnapp;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shawnapp.Model.Date;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>
{
    private final ArrayList<Date> daysOfMonth;
    private final OnItemListener onItemListener;
    private final YearMonth yearMonth;

    public CalendarAdapter(ArrayList<Date> daysOfMonth, OnItemListener onItemListener, YearMonth yearMonth)
    {
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
        holder.dayOfMonth.setText(daysOfMonth.get(position).getDate());
        if(daysOfMonth.get(position).getDate() == null){
            holder.point.setText("");
        } else {
            holder.point.setText(String.valueOf(daysOfMonth.get(position).getAverage()));
        }
    }

    @Override
    public int getItemViewType(int position){
        if(String.valueOf(yearMonth).equals(String.valueOf(YearMonth.from(LocalDate.now())))){
            if(daysOfMonth.get(position).getDate() != null){
                if(Integer.parseInt(daysOfMonth.get(position).getDate()) == LocalDate.now().getDayOfMonth()){
                    return 1;
                }
            }
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
}

