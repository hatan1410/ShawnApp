package com.myapp.shawnapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView tvDayOfMonth, tvAverage;
    private final CalendarAdapter.OnItemListener onItemListener;
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        tvDayOfMonth = itemView.findViewById(R.id.cellDayText);
        tvAverage = itemView.findViewById(R.id.tv_point);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getBindingAdapterPosition(), (String) tvDayOfMonth.getText());
    }
}
