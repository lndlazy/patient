package com.ylean.cf_hospitalapp.popular.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.cf_hospitalapp.R;
import com.ylean.cf_hospitalapp.my.adapter.AreaAdapter;
import com.ylean.cf_hospitalapp.my.bean.AreaEntry;
import com.ylean.cf_hospitalapp.popular.bean.DiseaseListEntry;

import java.util.List;

/**
 * Created by linaidao on 2019/1/8.
 */

public class DiseaseListAdapter extends RecyclerView.Adapter<DiseaseListAdapter.MyViewHolder> {

    private Context context;
    private List<DiseaseListEntry.DataBean> diseaseList;

    public DiseaseListAdapter(Context context, List<DiseaseListEntry.DataBean> data) {
        this.context = context;
        this.diseaseList = data;
    }

    @Override
    public DiseaseListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiseaseListAdapter.MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_disease_list, parent, false));
    }

    @Override
    public void onBindViewHolder(DiseaseListAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(diseaseList.get(position).getName());
        holder.tvContent.setText(diseaseList.get(position).getTabloid());
    }

    @Override
    public int getItemCount() {
        return diseaseList == null ? 0 : diseaseList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvContent;

        MyViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvContent = view.findViewById(R.id.tvContent);
        }
    }
}