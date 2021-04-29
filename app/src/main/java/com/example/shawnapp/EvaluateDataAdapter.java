package com.example.shawnapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shawnapp.Model.Category;

import java.util.List;

public class EvaluateDataAdapter extends RecyclerView.Adapter<EvaluateDataAdapter.MyViewHolder> {

    private final EvaluateActivity evaluateActivity;
    private final List<Category> categories;
    public double average;

    public EvaluateDataAdapter(EvaluateActivity evaluateActivity, List<Category> categories) {
        this.evaluateActivity = evaluateActivity;
        this.categories = categories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryView;
        categoryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category, parent, false);
        return new MyViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        String cate_name = categories.get(position).getCateName();
        int point = categories.get(position).getPoint();

        holder.tvCateName.setText(cate_name);
        holder.tvPoint.setText(point +"%");
        holder.seekBar.setProgress(point);
        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                holder.tvPoint.setText(i +"%");
                categories.get(position).setPoint(i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                evaluateActivity.setChanged(true);
                for(int i=0; i<categories.size(); i++){
                    evaluateActivity.setSum(evaluateActivity.getSum() + categories.get(i).getPoint());
                }
                average = evaluateActivity.getSum() / categories.size();
                if (average == (int) average) {
                    evaluateActivity.getTvProgress().setText(String.valueOf((int) average));
                }
                else {
                    evaluateActivity.getTvProgress().setText(String.valueOf(average));
                }
                evaluateActivity.getProgressBar().setProgress((int) average);
                evaluateActivity.setSum(0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvCateName, tvPoint;
        SeekBar seekBar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCateName = itemView.findViewById(R.id.tv_cate);
            tvPoint = itemView.findViewById(R.id.tv_point);
            seekBar = itemView.findViewById(R.id.seek_bar);
        }
    }
}
