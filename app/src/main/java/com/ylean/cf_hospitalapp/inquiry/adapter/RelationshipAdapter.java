package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;

import java.util.List;

public class RelationshipAdapter extends RecyclerView.Adapter<RelationshipAdapter.MyViewHolder> {

    private Context context;
    private List<String> strs;

    public RelationshipAdapter(Context context, List<String> strs) {
        this.context = context;
        this.strs = strs;
    }

    @Override
    public RelationshipAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RelationshipAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_string, parent, false));
    }

    @Override
    public void onBindViewHolder(RelationshipAdapter.MyViewHolder holder, int position) {

        holder.tvContent.setText(strs.get(position));
    }

    @Override
    public int getItemCount() {
        return strs == null ? 0 : strs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        MyViewHolder(View view) {
            super(view);
            tvContent = view.findViewById(R.id.tvContent);
        }
    }
}