package com.myapp.shawnapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewCateAdapter extends RecyclerView.Adapter<NewCateAdapter.MyViewHolder>  {

    NewCateActivity newCateActivity;
    List<String> list_cate;

    public NewCateAdapter(NewCateActivity newCateActivity, List<String> list_cate) {
        this.newCateActivity = newCateActivity;
        this.list_cate = list_cate;
    }

    @NonNull
    @Override
    public NewCateAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cateItem;
        cateItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cate_item, parent, false);
        return new MyViewHolder(cateItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NewCateAdapter.MyViewHolder holder, int position) {
        final String cate_name = list_cate.get(position);
        holder.tvCate.setText(cate_name);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newCateActivity.dialogEditCate(cate_name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_cate == null ? 0 : list_cate.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvCate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            tvCate = itemView.findViewById(R.id.tv_cate);
        }
    }
}
