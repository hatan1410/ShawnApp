package com.example.shawnapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewCateAdapter extends RecyclerView.Adapter<NewCateAdapter.MyViewHolder>  {

    Context context;
    List<String> list_cate;

    public NewCateAdapter(Context context, List<String> list_cate) {
        this.context = context;
        this.list_cate = list_cate;
    }

    @NonNull
    @Override
    public NewCateAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cateItem;
        cateItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cate_item, parent, false);
        return new NewCateAdapter.MyViewHolder(cateItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NewCateAdapter.MyViewHolder holder, int position) {
        String cate_name = list_cate.get(position);
        holder.tvCate.setText(cate_name);
    }

    @Override
    public int getItemCount() {
        return list_cate == null ? 0 : list_cate.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCate = itemView.findViewById(R.id.tv_cate);
        }
    }
}
