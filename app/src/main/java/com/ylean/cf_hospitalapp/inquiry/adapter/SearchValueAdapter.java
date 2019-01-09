package com.ylean.cf_hospitalapp.inquiry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SearchValueAdapter extends RecyclerView.Adapter<SearchValueAdapter.MyViewHolder> {

    private Context context;
    private Set<String> historySet = new LinkedHashSet<>();
    private List<String> historyList = new ArrayList<>();

    public SearchValueAdapter(Context context, Set<String> historySet) {
        this.context = context;
        this.historySet = historySet;
    }

    @Override
    public SearchValueAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new SearchValueAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_string, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchValueAdapter.MyViewHolder holder, int position) {

        historyList.clear();
        historyList.addAll(historySet);
        holder.tvContent.setText(historyList.get(position));
    }

    @Override
    public int getItemCount() {

        return historySet == null ? 0 : historySet.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;

        MyViewHolder(View view) {
            super(view);

            tvContent = (TextView) view.findViewById(R.id.tvContent);

        }
    }
}